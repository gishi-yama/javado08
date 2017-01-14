package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(Link.onClick("toSamplePage", (link) -> setResponsePage(new SamplePage()))
			.setBody(Model.of("toSamplePage")));

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));


	}
}
