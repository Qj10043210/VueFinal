import { BASE_URL, HOME_URL } from "./url.js";
import * as ld from "./load.js";
import * as fc from "./functions.js";
import * as bd from "./board.js";
import * as rs from "./rooms.js";

var cUrl = BASE_URL + "/client/"
var gUrl = BASE_URL + "/game/"
var bUrl = BASE_URL + "/board/"
var jsUrl = "/js/"
var lUrl = BASE_URL + "/lg/"
var rootUrl = "/"
var resUrl = "/res/"
const musicPlay03 = new Audio(resUrl+'3.mp3');
let music = true;
var lorr = jQ('#sessionNew').val();
var curName = "";
var cssUrl = "/css/"
const err = document.getElementById('error');
var tempReady = {};
var tempRRR = 0;
var tempTTT = false;
var tempname ="";
let tempQQQ = {
    "lroom": jQ('#sessionLoc').val(),
    "btype": jQ('#sessionTyp').val()
}
var uI;

var hUrl = jsUrl + "hair.json";
var fUrl = jsUrl + "face.json";


const l_url = fUrl;
let facejson;
const l_url2 = hUrl;
let hairjson;
let OtherFace, OtherHair, ThisFace, ThisHair;
jQ(document).on('click','body',function(){
	if(music){
	music = false;
	musicPlay03.loop=true;
	musicPlay03.play()
	}
})
ld.jsonData((data1) => {
    facejson = data1;
    
    ld.jsonData((data2) => {
        hairjson = data2;
        bringFaceHair(jQ("#sessionId").val(), lorr);
    }, l_url2)
}, l_url)

jQ(document).ready(function () {
    ld.loadWell(jQ('#globalLoad'));
    if (ld.checkLoged(HOME_URL, jQ('#sessionId'), err, jQ('#error_button'))) {
        console.log(jQ('#sessionNew').val());
        uI = setInterval(updateReady, 800);
        curName = jQ("#sessionId").val();
    }//last
})//lastss


jQ(document).on("click", '.btn_back', function () {
    let tempSource = {
        "lroom": jQ('#sessionLoc').val(),
        "pname": jQ("#sessionId").val(),
        "btype": jQ('#sessionTyp').val(),
    };
        
    function leaveRRSP(callback) {
        ld.leaveRoom(callback, gUrl, tempSource);
    }
    leaveRRSP(data => {
        window.location.href = HOME_URL + `/index`;
    });
});

jQ(document).on("click", '#rsp_btn_ready', function () {
    if (tempRRR == 0) {
        tempRRR = 1;
    } else {
        tempRRR = 0;
    }
    console.log(tempRRR);
    tempReady["lroom"] = parseInt(jQ('#sessionLoc').val());
    tempReady["pname"] = jQ("#sessionId").val().toString();
    tempReady["btype"] = parseInt(jQ('#sessionTyp').val());
    tempReady["rolr"] = lorr.toString();
    tempReady["rore"] = parseInt(tempRRR);

    readySituation(data => {
        let tempq = 0;
        lorr == "L" ? tempq = 0 : tempq = 1;
        console.log("this lorr ",lorr," this tempq ",tempq)
        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_0')
        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_1')

    });
});

function readySituation(callback) {
    rs.readySituation(callback, tempReady, lUrl);
}
function updateReadyAJAX(callback) {
    rs.updateReady(callback, tempQQQ, lUrl)
}


function updateReady() {
    if (jQ('#rsp_chars_ready_0').hasClass('rsp_chars_ready_1') && jQ('#rsp_chars_ready_1').hasClass('rsp_chars_ready_1')) {
        clearInterval(uI);
        jQ('#rsp_btn_ready').fadeOut(300);
    } else {
        updateReadyAJAX(data => {
            if (data.length > 0) {
                console.log("room's length ",data.length)
                console.table(data)
                for (let index = 0; index < data.length; index++) {
                    let item = data[index];
                    let tempq = 0;
                    let tempp = 0;
                    item.rolr == "L" ? tempq = 0 : tempq = 1;
                    if (item.rore != tempTTT && item.rolr != lorr) {
                        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_0')
                        jQ(`#rsp_chars_ready_${tempq}`).toggleClass('rsp_chars_ready_1')
                        tempTTT = item.rore;
                    }

                }
                
                let tempArray = [];                
                for (let index = 0; index < data.length; index++) {
                	let tempList = {};	
                    let item = data[index];
                    tempList["pname"] = item.pname;                    
                    tempList["rolr"] = item.rolr;                    
                    tempArray.push(tempList)
                }
                
                if(tempArray.length == 1){
                tempname ="";
                	let tempq = 0;
                	lorr == "L" ? tempq = 1 : tempq = 0;
                	jQ(`#rsp_chars_hair_${tempq}`).css({					
					width: `calc(100% * (38 / 57))`,
                    aspectRatio: `38 / 22}`,
                    top: `calc(100% * (14 / 50))`,
                    left: `calc(100% * (9 / 57))`,
                    backgroundImage: `url('${cssUrl}hair/0000.png')`
				});
				jQ(`#rsp_chars_face_${tempq}`).css({
					aspectRatio: `26 / 16`,
                    backgroundImage: `url('${cssUrl}face/0000.png')`
				})
                }else if(tempArray.length >1){
                tempArray.forEach(item=>{
                	if(item.pname != tempname && item.rolr != lorr){
                		tempname = item.pname;
                		bringFaceHair(item.pname, item.rolr);
                	}
                })
                }
                
            }

        })
    }
}

function bringFaceHair(names,lor){
	jQ.ajax({
		url : cUrl + "getUserInfo",
		type:'POST',
		data : {pname : names},
		success:function(data){
				let tempq = 0;
				lor == "L" ? tempq = 0 : tempq = 1;
				
				OtherFace = facejson.filter(element => element.face_original == data[0].pface);
				OtherHair = hairjson.filter(element => element.hair_original == data[0].phair);
				
				jQ(`#rsp_chars_hair_${tempq}`).css({					
					width: `calc(100% * (${OtherHair[0].hair_w} / 57))`,
                    aspectRatio: `${OtherHair[0].hair_w} / ${OtherHair[0].hair_h}`,
                    top: `calc(100% * (${OtherHair[0].hair_y} / 50))`,
                    left: `calc(100% * (${OtherHair[0].hair_x} / 57))`,
                    backgroundImage: `url('${cssUrl}hair/${OtherHair[0].hair_number}.png')`
				});
				jQ(`#rsp_chars_face_${tempq}`).css({
					aspectRatio: `${OtherFace[0].face_x} / ${OtherFace[0].face_y}`,
                    backgroundImage: `url('${cssUrl}face/${OtherFace[0].face_number}.png')`
				})
		},
		error: function (errorThrown) {
		}
		
		
	})
}