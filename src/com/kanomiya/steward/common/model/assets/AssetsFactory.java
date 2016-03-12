package com.kanomiya.steward.common.model.assets;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Locale;
import java.util.PropertyResourceBundle;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.I18n;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.event.Event;


/**
 * @author Kanomiya
 *
 */
public class AssetsFactory {

	protected static final ExtensionFilter jsonFilter = new ExtensionFilter("json");
	protected static final ExtensionFilter imageFilter = new ExtensionFilter("png");

	protected AssetsFactory() {  }


	protected String assetsDir, saveDir;

	public static AssetsFactory newInstance()
	{
		AssetsFactory afact = new AssetsFactory();

		afact.setAssetsDir("assets");
		afact.setSaveDir("saves");

		return afact;
	}

	public void setAssetsDir(String assetsDir)
	{
		this.assetsDir = assetsDir;
	}

	public void setSaveDir(String saveDir)
	{
		this.saveDir = saveDir;
	}


	public Assets newAssets()
	{
		Assets assets = new Assets(assetsDir, saveDir);

		Gson gson = AssetsUtils.createGson(assets);

		try {
			initTextures(gson, assets);
			initTips(gson, assets);
			initAreas(gson, assets);
			initEvents(gson, assets);
			initScripts(gson, assets);
			initI18n(assets);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assets.setLocale(Locale.getDefault());

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
				String path = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				path = path.replace('\\', '/');

				Image image = ImageIO.read(f);

				assets.cacheImage(path, image);
				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initTips(Gson gson, Assets assets) throws IOException
	{
		File root = new File(assetsDir, "tip");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				// String path = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				// path = path.replace('\\', '/');

				FileReader fr = new FileReader(f);
				Tip tipObj = gson.fromJson(fr, Tip.class);

				assets.addTip(tipObj);

				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initAreas(Gson gson, Assets assets) throws IOException
	{
		File root = new File(assetsDir, "area");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				// String path = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				// path = path.replace('\\', '/');

				FileReader fr = new FileReader(f);
				Area areaObj = gson.fromJson(fr, Area.class);

				assets.addArea(areaObj);

				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initEvents(Gson gson, Assets assets) throws IOException
	{
		File root = new File(assetsDir, "event");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				String path = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				path = path.replace('\\', '/');

				FileReader fr = new FileReader(f);

				// boolean isPlayer = file.getFileName().endsWith("player.json");
				Event eventObj = gson.fromJson(fr, Event.class);

				eventObj.area.setEvent(eventObj);
				assets.addEvent(eventObj);

				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initScripts(Gson gson, Assets assets) throws IOException
	{
		File root = new File(assetsDir, "script");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".js")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				String path = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				path = path.replace('\\', '/');

				InputStreamReader isrScript = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);

				StringBuilder sb = new StringBuilder();

				while (isrScript.ready())
				{
					sb.appendCodePoint(isrScript.read());
				}

				isrScript.close();

				assets.addScriptCode(path, sb.toString());

				return FileVisitResult.CONTINUE;
			}
		});

	}


	/**
	 * @param assets
	 */
	protected void initI18n(Assets assets) throws IOException {
		File root = new File(assetsDir, "lang");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".lang")) return FileVisitResult.CONTINUE;
				File f = file.toFile();

				String name = file.getFileName().toString();
				name = name.substring(0, name.lastIndexOf('.'));

				PropertyResourceBundle bundle = new PropertyResourceBundle(new FileReader(f));

				String[] tags = name.split("_");
				if (tags.length < 2) return FileVisitResult.CONTINUE;

				Locale.Builder builder = new Locale.Builder().setLanguage(tags[0]).setRegion(tags[1]);
				if (3 <= tags.length) builder.setVariant(tags[2]);

				assets.addI18n(new I18n(builder.build(), bundle));

				return FileVisitResult.CONTINUE;
			}
		});

	}






}

