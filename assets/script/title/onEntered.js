
player.setVisible(false);
player.disallowMode(PlayerMode.static.SELECT);

var msg = message().lockClose();

var c_1 = choice('a', translate("vocabulary.gameStart"), function(ch)
		{
			player.setVisible(true);
			player.allowMode(PlayerMode.static.SELECT);
			msg.setClosable(true);
			player.travel("test", 5,5);
		});

var c_2 = choice('b', translate("vocabulary.exit"), function(ch)
		{
			exit();
		});


showMessage(msg.println(c_1).println(c_2));
