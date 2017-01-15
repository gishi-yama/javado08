package com.example.page;

import com.example.service.IGuavaCache;
import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.LoadableDetachableModel;

import java.time.LocalDateTime;
import org.apache.wicket.util.time.Duration;

import static org.apache.wicket.ajax.AbstractAjaxTimerBehavior.onTimer;
import static org.apache.wicket.util.time.Duration.ONE_SECOND;
import static org.apache.wicket.util.time.Duration.seconds;

public class SamplePage extends WebPage {

	@Inject
	private IGuavaCache cache;

	public SamplePage() {

		add(new Label("dateTime", LoadableDetachableModel.of(LocalDateTime::now))
			.setOutputMarkupId(true)
			.add(onTimer(seconds(1), (t) -> t.add(getPage().get("dateTime")))));

		add(new Label("random1", LoadableDetachableModel.of(cache::getRandomInt))
			.setOutputMarkupId(true)
			.add(onTimer(seconds(1), (t) -> t.add(getPage().get("random1")))));

		add(new Label("cache1", LoadableDetachableModel.of(() -> cache.getCachedRandomInt("cache1")))
			.setOutputMarkupId(true)
			.add(onTimer(seconds(5), (t) -> t.add(getPage().get("cache1")))));


	}
}
