//ps当前文档
var pngDoc = app.activeDocument;
//保存名字为当前文档名字
//var iconName = pngDoc.name.split(".")[0] + ".png";

//调用[Folder]的[selectDialog]命令，弹出文件夹选择窗口，提示用户选择输出iOS图标的文件夹。
//并将文件夹存储在变量[destFolder]中。
var destFolder = Folder.selectDialog( "请选择一个输出的文件夹：");

if(destFolder != null){
    //定义一个数组，这个数组由各种js对象组成，每个对象都有一个[name]属性和[size]属性，分别表示图标的名称的尺寸。
    var icons = 
    [
	 {"dir": "16",             "size":"16px"},
	{"dir": "28",             "size":"28px"},
	{"dir": "48",             "size":"48px"},
	{"dir": "72",             "size":"72px"},
	{"dir": "75",             "size":"75px"},
	{"dir": "80",             "size":"80px"},
	{"dir": "90",             "size":"90px"},
	{"dir": "96",             "size":"96px"},
	{"dir": "100",             "size":"100px"},
	{"dir": "108",             "size":"108px"},
	{"dir": "120",             "size":"120px"},
	{"dir": "136",             "size":"136px"},
	{"dir": "144",             "size":"144px"},
	{"dir": "168",             "size":"168px"},
	{"dir": "192",             "size":"192px"},
	{"dir": "512",             "size":"512px"}
    ];

    //定义一个变量[option]，表示iOS输出的格式为PNG。并设置输出PNG时不执行PNG8压缩，以保证图标质量。
    var option = new PNGSaveOptions();
    option.PNG8 = false;

    //保存当前的历史状态，以方便缩放图片后，再返回至最初状态的尺寸。
    var startState = pngDoc.historyStates[0];

    //添加一个循环语句，用来遍历所有图标对象的数组。
    for (var i = 0; i < icons.length; i++) 
    {
        //定义一个变量[icon]，表示当前遍历到的图标对象。
        var icon = icons[i];

        //调用[pngDoc]对象的[resizeImage]方法，将原图标，缩小到当前遍历到的图标对象定义的尺寸。
        pngDoc.resizeImage(icon.size, icon.size);

        //创建目录
       // var targetFolder = new Folder(destFolder + "/" + icon.dir);
        //targetFolder.create();

        //定义一个变量[file]，表示图标输出的路径。
        var file = new File(destFolder +  "/" + icon.dir);

        //调用[pngDoc]的[saveAs]方法，将缩小尺寸后的图标导出到指定路径。
        pngDoc.saveAs(file, option, true, Extension.LOWERCASE);

        //将[doc]对象的历史状态，恢复到尺寸缩放之前的状态，即恢复到原始尺寸，为下次缩小尺寸做准备。
        pngDoc.activeHistoryState = startState;
    }
}