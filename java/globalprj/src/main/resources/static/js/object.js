import { BASE_URL } from "./url.js";
import * as rd from "./rendering.js";
import * as fc from "./functions.js";
import * as ld from "./load.js";

const l_url = "/res/map.json"

ld.jsonData(data=>{
    let mapjson = data;
    let count_object = 0;
    let count_natural = 0;
    mapjson.forEach(item=>{
        if(item.map_name ==="object"){
            count_object ++;
        }else if(item.map_name ==="natural"){
            count_natural ++;
        }
    })
    let tempArs = getArray(rd.g_matrix_x,rd.g_objqty);
    let tempArs2 = getArray(rd.g_matrix_y,rd.g_objqty)
    function getArray(original_array, objs){
        let tempArs = [];
        let tempAr = original_array;
        let tempN = parseInt(objs)>=tempAr.length ? parseInt(objs) :tempAr.length;
        for(let i =0; i<tempN; i++){
            let tempS = tempAr.length;
            let tempN = fc.functions_RandomStartEndSize(0,tempS-1,tempS*10);
            if(tempAr[tempN] != null && tempAr[tempN] >=0){
                tempArs.push(tempAr[tempN]);
                tempAr.splice(tempN, 1);
            }
        }
        return tempArs;
    }
    setObj(tempArs, tempArs2, rd.g_objqty, rd.g_objsize, rd.g_bc)
    function setObj(ax, ay, objs, objSize, original){
        for(let i =0; i<parseInt(objs); i++){
            let tempX = `${ax[i] * objSize}px`;
            let tempY = `${ay[i] * objSize}px`;
            //let tempT = fc.functions_RandomStartEndSize(0,10,10) > 5 ? true : false;
            let tempT = true;
            let tempN0 = tempT ? fc.functions_RandomStartEndSize(0,count_natural-1,count_natural*10)  : fc.functions_RandomStartEndSize(0,count_object-1,count_object*10);
            tempN0<10? tempN0 ="0" + tempN0: tempN0 = tempN0
            let tempN1 = tempT ? "w"+tempN0 : "q"+tempN0
            let tempItem = mapjson.find(item=>item.map_tag == tempN1);
            let tempW=0, tempH =0;
            if(tempItem != null){
                tempW = tempItem.map_x;
                tempH = tempItem.map_y;
                
            }
            let tempString = `<div class="g_object g_objcect_${i}" style="position:absolute; top:${tempY}; 
                left:${tempX}; 
                aspect-ratio : ${tempW}/ ${tempH};
                width:${objSize*1.5}px;
                background-image:url('./css/map/`
            
                
                
            tempString    +=`${tempN1}.png');
                background-repeat:no-repeat;
                background-size:100% auto;"></div>`
            
            $(original).prepend(tempString)
        }
    }
























}, l_url)
