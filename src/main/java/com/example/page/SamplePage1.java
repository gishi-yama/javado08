package com.example.page;

import com.example.service.IGuavaCache;
import com.google.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.LoadableDetachableModel;

import java.time.LocalDateTime;

import static org.apache.wicket.ajax.AbstractAjaxTimerBehavior.onTimer;
import static org.apache.wicket.util.time.Duration.ONE_SECOND;

public class SamplePage1 extends WebPage {

	@Inject
	private IGuavaCache cache;

	public SamplePage1() {

		add(new Label("dateTime", LoadableDetachableModel.of(LocalDateTime::now))
			.setOutputMarkupId(true)
			.add(onTimer(ONE_SECOND, (t) -> t.add(getPage().get("dateTime")))));

		add(new Label("random1", LoadableDetachableModel.of(cache::getRandomInt))
			.setOutputMarkupId(true)
			.add(onTimer(ONE_SECOND, (t) -> t.add(getPage().get("random1")))));

		add(new Label("cache1", LoadableDetachableModel.of(() -> cache.getCachedRandomInt("cache1")))
			.setOutputMarkupId(true)
			.add(onTimer(ONE_SECOND, (t) -> t.add(getPage().get("cache1")))));


	}
}
