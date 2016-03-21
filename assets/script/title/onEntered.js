
player.setVisible(false);

var msg = message().lockClose();

var c_z = choice('z', "戻る").confirmHandlerJS(function()
		{
			showWindow(msg);
		});


var c_1 = choice('a', translate("vocabulary.gameContinue")).confirmHandlerJS(function()
		{

		});


var c_2 = choice('b', translate("vocabulary.gameStart")).confirmHandlerJS(function()
		{
			showWindow(message().lockClose()
				.println("セーブデータ名 (使用不可：[\\0][.][/]['][\"][`][\\t][\\n][\\r][\\f][?][*][\\][<][:][|][>])")
				.println(textField().confirmHandlerJS(function(text)
					{

						if (assets.setSaveName(text))
						{
							player.removeWindow();

							player.setVisible(true);
							msg.setClosable(true);
							player.travel("test", 5,5);

						}

					}))
			);

		});

var c_3 = choice('c', translate("vocabulary.exit")).confirmHandlerJS(function()
		{
			exit();
		});


var c_4 = choice('d', translate("vocabulary.test")).confirmHandlerJS(function()
		{
			var c_a = choice('a', "長文テスト").confirmHandlerJS(function()
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

			var c_b = choice('b', "入力").confirmHandlerJS(function()
					{
						var msg_input = message().lockClose()
							.println("入力待機中")
							.println("")
							.println(textField("i -> ").confirmHandlerJS(function(text)
							{
								logger.println(text);
							}))
							.println("")
							.println(c_z);


						showWindow(msg_input);
					});

			showWindow(message().lockClose().println(c_a).println(c_b).println(c_z));
		});


showWindow(msg.println(c_1).println(c_2).println(c_3).println(c_4));
