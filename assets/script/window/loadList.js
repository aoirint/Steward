// window/loadList


var sfs;
var sfNames;
var chToSfs;

function loadListInit()
{
	sfs = saveFiles();
	sfNames = new Array();
	chToSfs = new Array();


	for (var i=0; i<sfs.size(); i++)
	{
		var ch = binder.nextChar('a', i);

		sfNames[i] = sfs[i].name;
		chToSfs[ch] = sfs[i];
	}

}

