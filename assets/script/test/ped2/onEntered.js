
logger.println(text(translate("se.pop2")).color(GameColor.static.green));


var c_1 = choice("Demo").confirmHandlerJS(function(ch)
	{
		logger.println(text("You choiced " + ch));
		player.showWindow(message().println("You choiced [" + ch + "]\n ").println("Choice page was replaced with new message(this)"));

	});

var c_2 = choice("Travel for " + translate("area.test2")).confirmHandlerJS(function(ch)
	{
		player.travel("test2", 5,5);

	});

showWindow(message().println("This book has two choices.\n ").println(c_1).println(c_2));





