package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.script.ScriptEventType;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTEvent extends ResourceType<Event> {

	protected RTEvent() {
		super(false, true, "event", new ExtensionFilter("json", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Event load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException
	{

		Event event = gson.fromJson(new FileReader(file), Event.class);

		futureTaskList.add(new FutureTask(new Callable<Boolean>()
		{
			@Override
			public Boolean call() {
				event.area.setEvent(event);
				event.area.launchEvent(event, event.x, event.y, ScriptEventType.ONENTERED); // TODO change ?? INITED

				return true;
			}
		}));

		return event;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Event resource, File file, Gson gson) throws IOException
	{
		AssetsUtils.saveAsJson(resource, gson, file); // TODO フォルダ作成
	}





}
