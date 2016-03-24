
player.setVisible(false);

var msg = message().lockClose();

exec("window/loadList");

loadListInit();

exec("window/back");



var c_1 = choice(translate("vocabulary.gameContinue")).confirmHandlerJS(function()
		{
			var c_msg = message().lockClose();

			var c_func = function(ch)
			{
				var sf = chToSfs[ch];
				player.removeWindow();

				player.setVisible(true);

				assets.setSaveName(sf.name);
				assets.load();

			};

			for (var i=0; i<sfNames.length; i++)
			{
				c_msg.println(choice(sfNames[i]).confirmHandlerJS(c_func));
			}

			c_msg.println("").println(backChoice(msg));

			showWindow(c_msg);

		}).enabled(0 < sfs.length);


var c_2 = choice(translate("vocabulary.gameStart")).confirmHandlerJS(function()
		{
			var s_msg = message().lockClose()
					.println("セーブデータ名 (使用不可：[\\0][.][/]['][\"][`][\\t][\\n][\\r][\\f][?][*][\\][<][:][|][>])")
					.println(textField().confirmHandlerJS(function(text)
						{

							if (sfNames.indexOf(text) == -1 && assets.setSaveName(text))
							{
								player.removeWindow();

								player.setVisible(true);
								player.travel("test", 5,5);

							}

						}))
					.println("")
					.println(backChoice(msg));

			showWindow(s_msg);

		});

var c_3 = choice(translate("vocabulary.exit")).confirmHandlerJS(function()
		{
			exit();
		});


var c_4 = choice(translate("vocabulary.test")).confirmHandlerJS(function()
		{
			var c_a = choice("長文テスト").confirmHandlerJS(function()
			{
				showWindow(messageBook().lockClose()
						.println(backChoice(t_msg))
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

			var c_b = choice("入力").confirmHandlerJS(function()
					{
						var msg_input = message().lockClose()
							.println("入力待機中")
							.println("")
							.print("i -> ").println(textField().confirmHandlerJS(function(text)
							{
								logger.println(text);
							}))
							.println("")
							.println(backChoice(t_msg));


						showWindow(msg_input);
					});

			var t_msg = message().lockClose().println(c_a).println(c_b).println(backChoice(msg));

			showWindow(t_msg);
		});


showWindow(msg.println(c_1).println(c_2).println(c_3).println(c_4));
