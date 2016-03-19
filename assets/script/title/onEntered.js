
player.setVisible(false);
player.disallowMode(PlayerMode.static.SELECT);

var msg = message().lockClose();

var c_z = choice('z', "戻る", function()
		{
			showWindow(msg);
		});


var c_1 = choice('a', translate("vocabulary.gameStart"), function()
		{
			player.setVisible(true);
			player.allowMode(PlayerMode.static.SELECT);
			msg.setClosable(true);
			player.travel("test", 5,5);
		});

var c_2 = choice('b', translate("vocabulary.exit"), function()
		{
			exit();
		});


var c_3 = choice('c', translate("vocabulary.test"), function()
		{
			var c_a = choice('a', "長文テスト", function()
			{
				showWindow(messageBook().lockClose().println(c_z)
						.println(" ")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト", true)
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト")
						.println("長文テスト", true)
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆☆★★★★★□□□□□■■■■■")
						.print("☆☆☆☆", true)
						.print("長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト長文テスト")

						.goFirst()
						);
			});

			showWindow(message().lockClose().println(c_a).println(c_z));
		});


showWindow(msg.println(c_1).println(c_2).println(c_3));
