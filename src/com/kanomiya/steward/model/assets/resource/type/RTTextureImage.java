package com.kanomiya.steward.model.assets.resource.type;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.texture.TextureImage;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTTextureImage extends ResourceType<TextureImage> {

	protected RTTextureImage() {
		super(false, true, "image", new ExtensionFilter("json", false));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public TextureImage load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException
	{

		BufferedImage image = ImageIO.read(file);

		TextureImage texImg = new TextureImage(id, image);

		/* -> RTTransformerTextureImage
		 *
		File transformFile = new File(id + ".json");
		if (transformFile.exists())
		{
			TransformerTextureImage transformer = factory.gson.fromJson(new FileReader(file), TransformerTextureImage.class);
			texImg = transformer.toTextureImage();
		}
		*/

		return texImg;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(TextureImage resource, File file, Gson gson) throws IOException
	{
		// TODO 自動生成されたメソッド・スタブ

	}




}
