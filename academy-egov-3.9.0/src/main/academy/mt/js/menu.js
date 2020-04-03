
function menuInit(){
	$("#tree").dynatree({
		checkbox: true,
		selectMode: 3,
		expand : true,
		children: [
			{title: "교육과정관리"},
			{title: "Folder 2", isFolder: true, key: "node2",
				children: [
					{title: "Sub-item 2.1", name:"n_1", key: "node2.1", select:true },
					{title: "Sub-item 2.2", name:"n_2", key: "node2.2", select:true}
				]
			},
			{title: "Item 3", key: "node3"}
		]
	});
	
};