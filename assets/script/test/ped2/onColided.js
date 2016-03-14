
logger.println(text(translate("se.pop2")).color(GameColor.static.green));

// test(function(ch) { logger.println(text(new String(ch))); });

var c_1 = choice('a', "Demo", function(ch) {
	
	logger.println(text("You choiced " + ch));
	player.getMessage().replace(message().println("You choiced [" + ch + "]\n ").println("Choice page was replaced with new message(this)"));
	
	});

var c_2 = choice('b', "Travel for " + translate("area.test2"), function(ch) {
	
	player.travel("test2", 5,5);
	
	});

// showMessage(message().print("aiu"));
showMessage(message().println("This book has two choices.\n ").println(c_1).println(c_2));





