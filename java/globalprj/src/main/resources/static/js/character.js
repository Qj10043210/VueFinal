import { BASE_URL } from "./url.js";
import * as ac from "./action.js";
import * as rd from "./rendering.js";
import * as ld from "./load.js";
import * as fc from "./functions.js";

$(document).keydown(function(e){
    ac.g_pressed(e,rd.g_values)
})
const l_url = "./res/face.json"
let facejson;
const l_url2 = "./res/hair.json"
let hairjson;
const l_url3= "./res/temp.json"
const ch_no = 0;
ld.jsonData((data1) => {
    facejson = data1;
    ld.jsonData((data2) => {
        hairjson = data2;
        ld.jsonData((data) => {
            let tempjson = data;
            let chFilterasNo = tempjson.filter((ch)=>parseInt(ch.ch_no) == ch_no)[0];
            let hairFilterasNo = hairjson.filter((ha)=>ha.hair_original==chFilterasNo.ch_ha)
            let faceFilterasNo = facejson.filter((ha)=>ha.face_original==chFilterasNo.ch_fa)
            setCharacter(chFilterasNo, hairFilterasNo, faceFilterasNo, rd.g_values, rd.g_bc)
        }, l_url3)
    }, l_url2)
}, l_url)

function setCharacter(
    chInfo,
    hairInfo,
    faceInfo,
    mapInfo,
    original
    
){
    let tempString =
    `<div id="char${chInfo.ch_no}" class="char"
        data-face-org = "${faceInfo[0].face_original}";
        data-face-def = "${faceInfo[0].face_number}";
        data-face-def-x = "${faceInfo[0].face_x}";
    
    ></div>`
}