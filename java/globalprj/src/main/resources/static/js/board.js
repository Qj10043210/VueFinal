export function makeBoard(targets, infos, datas){
	let worked = 0;
	let maxN = infos["max"];
    let boxNa = infos["box-name"];
    let boxCl = infos["box-class"];
    let titleNa = infos["title-class"];
    let indexNa = infos["index-class"];
    for(let index=0; index < datas.length; index++){
        let item = datas[index];
        if(index >= maxN){break;}
        let tempText = `<div id="${boxNa}${index}" data-index="${index+1}" class="${boxCl}"> <span class="${titleNa}">${item.pname}</span><span class="${indexNa}">${item.btitle}</span><span class="playing${item.bplaying}">●</span></div>`
        targets.prepend(tempText)
        worked++;
    }
    return worked;
}

export function loadReadyPlayer(callback, urls){
	let emptyS = "empty";
	jQ.ajax({
		url : urls + "loadReadyPlayer",
        type: 'POST',        
        data: {emptyS	 : emptyS},
        success: function (data) {
            callback(data);
        },
        error: function (errorThrown) {
            
        }
	})
};