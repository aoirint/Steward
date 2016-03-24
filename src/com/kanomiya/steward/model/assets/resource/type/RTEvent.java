package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTEvent extends ResourceType<Event> {

	protected RTEvent() {
		super(true, true, "event", new ExtensionFilter("json", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Event load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException
	{

		Event event = gson.fromJson(new FileReader(file), Event.class);

		futureTaskList.add(new Consumer<Assets>()
		{
			@Override
			public void accept(Assets assets) {
				event.area.setEvent(event, true);
			}
		});


		return event;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Event resource, File baseDir, Gson gson) throws IOException
	{
		File file = new File(baseDir, resource.getId() + ".json");

		if (! file.getParentFile().exists()) file.getParentFile().mkdirs();

		AssetsUtils.saveAsJson(resource, gson, file);
	}





}
