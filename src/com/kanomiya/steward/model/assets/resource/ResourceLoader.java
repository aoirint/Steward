package com.kanomiya.steward.model.assets.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;

/**
 * @author Kanomiya
 *
 */
public class ResourceLoader<R extends IResource> {

	protected ResourceType<R> type;
	protected ResourceRegistry<R> registry;

	public ResourceLoader(ResourceType<R> type, ResourceRegistry defaulz)
	{
		this.type = type;
		if (defaulz == null) defaulz = new ResourceRegistry<R>();
		registry = defaulz;
	}


	private int loadedCount;

	public ResourceLoader<R> load(File baseDir, Gson gson, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(baseDir, type.getDirName());
		String typeName = type.getClass().getSimpleName();


		Game.logger.debug("####################################################");
		if (! root.exists())
		{
			Game.logger.debug("Loading " + typeName + " Skipped: Not Found");
			return this;
		}

		Game.logger.debug("Start Loading " + typeName + ": " + root.getPath());

		loadedCount = 0;

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				File f = file.toFile();

				if (type.hasFilter())
				{
					if (! type.getFilter().accept(f)) return FileVisitResult.CONTINUE;
				}

				String id = baseNameOf(root.getCanonicalPath(), f.getCanonicalPath()); // TODO baseName Or relativePath

				R resource;

				try
				{
					resource = type.load(id, f, gson, futureTaskList);
				} catch (IOException | JsonSyntaxException e)
				{
					Game.logger.warn("Exception at " + file.toString());
					throw e;
				}

				loadedCount ++;

				if (resource != null)
				{
					if (! id.equals(resource.getId()))
					{
						Game.logger.info("Registered with Resource id. F:" + id + " R:" + resource.getId() + " at " + file.toString());
					}

					registry.put(resource.getId(), resource);
					Game.logger.debug("Loaded " + typeName + ": " + resource.getId());

				}
				else Game.logger.warn("Resource is null: " + f.getCanonicalPath());

				return FileVisitResult.CONTINUE;
			}
		});

		Game.logger.debug("Finished Loading " + typeName + "(" + loadedCount + ")" + " -> Total(" + registry.size() + ")");

		return this;
	}


	public ResourceRegistry<R> toRegistry()
	{
		return registry;
	}


	protected static String baseNameOf(String rootPath, String filePath)
	{
		String relative = filePath.substring(rootPath.length() +1);
		relative = relative.replace('\\', '/');

		String baseName = relative.substring(0, relative.lastIndexOf('.'));

		return baseName;
	}


}
