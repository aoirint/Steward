// window/back

function backChoice(prev)
{
	return choice(translate("vocabulary.back")).confirmHandlerJS(function()
			{
				showWindow(prev);
			});
}


