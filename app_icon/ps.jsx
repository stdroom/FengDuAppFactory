//ps��ǰ�ĵ�
var pngDoc = app.activeDocument;
//��������Ϊ��ǰ�ĵ�����
//var iconName = pngDoc.name.split(".")[0] + ".png";

//����[Folder]��[selectDialog]��������ļ���ѡ�񴰿ڣ���ʾ�û�ѡ�����iOSͼ����ļ��С�
//�����ļ��д洢�ڱ���[destFolder]�С�
var destFolder = Folder.selectDialog( "��ѡ��һ��������ļ��У�");

if(destFolder != null){
    //����һ�����飬��������ɸ���js������ɣ�ÿ��������һ��[name]���Ժ�[size]���ԣ��ֱ��ʾͼ������Ƶĳߴ硣
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

    //����һ������[option]����ʾiOS����ĸ�ʽΪPNG�����������PNGʱ��ִ��PNG8ѹ�����Ա�֤ͼ��������
    var option = new PNGSaveOptions();
    option.PNG8 = false;

    //���浱ǰ����ʷ״̬���Է�������ͼƬ���ٷ��������״̬�ĳߴ硣
    var startState = pngDoc.historyStates[0];

    //���һ��ѭ����䣬������������ͼ���������顣
    for (var i = 0; i < icons.length; i++) 
    {
        //����һ������[icon]����ʾ��ǰ��������ͼ�����
        var icon = icons[i];

        //����[pngDoc]�����[resizeImage]��������ԭͼ�꣬��С����ǰ��������ͼ�������ĳߴ硣
        pngDoc.resizeImage(icon.size, icon.size);

        //����Ŀ¼
       // var targetFolder = new Folder(destFolder + "/" + icon.dir);
        //targetFolder.create();

        //����һ������[file]����ʾͼ�������·����
        var file = new File(destFolder +  "/" + icon.dir);

        //����[pngDoc]��[saveAs]����������С�ߴ���ͼ�굼����ָ��·����
        pngDoc.saveAs(file, option, true, Extension.LOWERCASE);

        //��[doc]�������ʷ״̬���ָ����ߴ�����֮ǰ��״̬�����ָ���ԭʼ�ߴ磬Ϊ�´���С�ߴ���׼����
        pngDoc.activeHistoryState = startState;
    }
}