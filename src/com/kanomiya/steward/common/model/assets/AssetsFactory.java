package com.kanomiya.steward.common.model.assets;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;


/**
 * @author Kanomiya
 *
 */
public class AssetsFactory {

	protected static final ExtensionFilter jsonFilter = new ExtensionFilter("json");
	protected static final ExtensionFilter imageFilter = new ExtensionFilter("png");

	protected AssetsFactory() {  }


	protected String assetsDir;

	public static AssetsFactory newInstance()
	{
		AssetsFactory afact = new AssetsFactory();

		afact.setAssetsDir("assets");

		return afact;
	}

	public void setAssetsDir(String assetsDir)
	{
		this.assetsDir = assetsDir;
	}


	public Assets newAssets()
	{
		Assets assets = new Assets(assetsDir);

		Gson gson = AssetsUtils.createGson(assets);

		try {
			initTextures(gson, assets);
			initTips(gson, assets);
			initAreas(gson, assets);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return assets;
	}



	protected void initTextures(Gson gson, Assets assets) throws IOException
	{
		File root = new File(assetsDir, "texture");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				File f = file.toFile();
				Image image = ImageIO.read(f);
				String path = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);

				path = path.replace('\\', '/');

				assets.addTexture(path, image);
				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initTips(Gson gson, Assets assets) throws FileNotFoundException
	{
		File[] tipFiles = new File(assetsDir, "tip").listFiles(jsonFilter);

		for (File f: tipFiles)
		{
			FileReader fr = new FileReader(f);
			Tip tipObj = gson.fromJson(fr, Tip.class);

			assets.addTip(tipObj);
		}

	}

	protected void initAreas(Gson gson, Assets assets) throws IOException
	{
		File[] areaFiles = new File(assetsDir, "area").listFiles(jsonFilter);

		for (File f: areaFiles)
		{
			FileReader fr = new FileReader(f);
			Area areaObj = gson.fromJson(fr, Area.class);

			initEvents(areaObj, gson, assets);

			assets.addArea(areaObj);
		}

	}

	protected void initEvents(Area area, Gson gson, Assets assets) throws IOException
	{
		File[] eventFiles = new File(assetsDir, "event/" + area.getId()).listFiles(jsonFilter);

		for (File f: eventFiles)
		{
			FileReader fr = new FileReader(f);
			Event eventObj = gson.fromJson(fr, Event.class);

			Map<ScriptEventType, Script> scripts = eventObj.scripts;

			if (scripts != null)
			{
				Iterator<Script> itr = scripts.values().iterator();

				while (itr.hasNext())
				{
					Script script = itr.next();

					InputStreamReader isrScript = new InputStreamReader(new FileInputStream(new File(assetsDir, "script/" + script.src)), StandardCharsets.UTF_8);

					StringBuilder sb = new StringBuilder();

					while (isrScript.ready())
					{
						sb.appendCodePoint(isrScript.read());
					}

					isrScript.close();

					assets.addScriptCode(script.src, sb.toString());

				}

			}


			area.setEvent(eventObj);
		}


	}






}

