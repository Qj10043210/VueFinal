import { BASE_URL,HOME_URL } from "./url.js";
import * as ld from "./load.js";
import * as fc from "./functions.js";
import * as bd from "./board.js";

var cUrl = BASE_URL + "/client/"
var gUrl = BASE_URL + "/game/"
var bUrl = BASE_URL + "/board/"
var jsUrl = "/js/"
var lUrl = BASE_URL + "/lg/"
var rootUrl = "/"
var resUrl = "/res/"
const musicPlay04 = new Audio(resUrl+'4.mp3');
let music = true;
var cssUrl = "/css/"
const err = document.getElementById('error');
const rsp_m = document.getElementById('ir_m');
const rsp_main = document.getElementById('ir_party_main');
const rsp_lv_t = document.getElementById('ir_party_lv_t');
const rsp_lv_v = document.getElementById('ir_party_lv_v');
const rsp_data_r = document.getElementById('ir_party_data_r');
const rsp_data_w = document.getElementById('ir_party_data_w');
const rsp_data_l = document.getElementById('ir_party_data_l');
const rsp_data_d = document.getElementById('ir_party_data_d');
const rsp_done = document.getElementById('ir_party_done');

var textMax = 7, textMaded = 0, tempData, tempUpdate = {};

/////////////////////////temp////////////////////
//
//
var expLimit = 1000;
const pageInfo = {
    "box-name": "ir_party_room",
    "box-class": "cr_party_room",
    "title-class": "cr_party_room_title",
    "index-class": "cr_party_room_index",
    "max": textMax
}
let uI;
//
//
//////////////////////////////////////////////////
jQ(document).on('click','body',function(){
	if(music){
	music = false;
	musicPlay04.loop=true;
	musicPlay04.play()
	}
})
jQ(document).ready(function () {
    ld.loadWell(jQ('#globalLoad'))
    if (ld.checkLoged(HOME_URL, jQ('#sessionId'), err, jQ('#error_button'))) {
    	clearAll();
        uI = setInterval(allDo, 700);
    }//last
});//lastss


function allDo(){
    jQ.ajax({
        url: gUrl + "getScoreRSP",
        type: 'GET',
        success: function (data) {
            
            tempData = data;            
            updateAll(data);
            loadBRSP(data => {
                let tmp = data;
                console.log(data)
                jQ('#ir_party_room_all').empty();
                loadReadyPlayer(data=>{  
                textMaded = bd.makeBoard(jQ('#ir_party_room_all'), pageInfo, tmp);
                tempUpdate["pname"] = jQ("#sessionId").val();
                tempUpdate["btype"] = 0;
                })
            })
        },
        error: function (errorThrown) {

        }
    });//fAjax
}
jQ(document).on("click", "#ir_party_newButton", function () {
    jQ('#ir_party_board').fadeIn(100);
});
jQ(document).on("click", "#ir_party_done", function () {
    window.location.href = HOME_URL + `/index`;
});
jQ(document).on("click", "#ir_party_board_de", function () {
    jQ('#ir_party_board_input').val("")
    jQ('#ir_party_board_input').attr("placeholder", "");
    jQ('#ir_party_board').fadeOut(100);
});
jQ(document).on("click", "#ir_party_board_in", function () {
    if (jQ('#ir_party_board_input').val() == null || jQ('#ir_party_board_input').val() == "") {
        jQ('#ir_party_board_input').attr("placeholder", "빈 글은 등록할 수 없습니다!");
    } else {
        jQ('#ir_party_board_input').attr("placeholder", "");
        tempUpdate["btitle"] = jQ('#ir_party_board_input').val();
        tempUpdate["lroom"] = parseInt(jQ('.cr_party_room_title').length) + 1;
        console.log(parseInt(jQ('.cr_party_room_title').length) + 1)
        updateBRSP(data => {
            loadBRSP(data => {
                console.table("board",data)
            	let tmp = data;
            	loadReadyPlayer(data=>{            	
                jQ('#ir_party_room_all').empty();
                textMaded = bd.makeBoard(jQ('#ir_party_room_all'), pageInfo, tmp);
                tempUpdate["pname"] = jQ("#sessionId").val();
                clearInterval(uI);
                roomMove(textMaded)
                })
            })
        })
    }

});
jQ(document).on('click', ".cr_party_room", function () {
    let nums = jQ(this).data("index")
    console.log("ttttt",nums)
    roomMove(nums);
})


function updateBRSP(callback) {
    ld.updateBoard(callback, bUrl, tempUpdate)
}
function loadReadyPlayer(callback) {
    bd.loadReadyPlayer(callback, lUrl)
}
function loadBRSP(callback) {
    ld.loadBoard(callback, bUrl, "0");
}

function clearAll() {
    jQ('#ir_party_board_input').val("")
    jQ('#ir_party_board_input').attr("placeholder", "");
    jQ('#ir_party_board').css('display', 'none');
    rsp_lv_v.textContent = "";
    rsp_data_r.textContent = "";
    rsp_data_w.textContent = "";
    rsp_data_l.textContent = "";
    rsp_data_d.textContent = "";
}
function updateAll(data) {
    rsp_lv_v.textContent = data[0].plevel;
    rsp_data_w.textContent = data[0].rwin;
    rsp_data_l.textContent = data[0].rlose;
    rsp_data_d.textContent = data[0].rdraw;
    let rate = 0, hps = 0, exps = 0;
    if ((data[0].rwin + data[0].rdraw + data[0].rlose) != 0) {
        rate = ((data[0].rwin + data[0].rdraw) / (data[0].rwin + data[0].rdraw + data[0].rlose)) * 100;
    }
    rate = rate.toFixed(2)
    rsp_data_r.textContent = rate;
    if (data[0].pcurhp != 0) {
        hps = (data[0].pcurhp / data[0].pmaxhp) * 100;
    }
    exps = (data[0].pexp / expLimit) * 100;
    jQ('#ir_status_bar_hp_b').css({
        clipPath: `polygon(0% 0%, ${hps}% 0%, ${hps}% 100%, 0% 100%)`
    });
    jQ('#ir_status_bar_exp_b').css({
        clipPath: `polygon(0% 0%, ${exps}% 0%, ${exps}% 100%, 0% 100%)`
    })

}
function roomMove(nums){
    
    jQ("#globalLoad").css({
        display: 'block',
        backgroundImage: 'none',
        // backgroundImage : `url('${cssUrl}loadBack.png')`
    });
    let tempSource = {
        "lroom": nums,
        "btype": 0,
        "pname": jQ("#sessionId").val()
    };
    function tryRRSP(callback) {
        ld.tryRoom(callback, gUrl, tempSource)
    }
    function enterRRSP(callback) {
        ld.enterRoom(callback, gUrl, tempSource)
    }


    tryRRSP(data => {
        console.log("tryRoom",data)
        switch (data) {
            case "Y":
                enterRRSP(data => {
                    console.log(data)
                    if (data == "w") {
                        window.location.href = HOME_URL + `/room?num=` + tempSource.lroom;
                    };
                    jQ("#globalLoad").css({
                        display: 'none',
                        backgroundImage: `url('${cssUrl}loadBack.png')`
                    });
                })
                break;
            case "S":
                enterRRSP(data => {
                    console.log(data)
                    if (data == "w") {
                        window.location.href = HOME_URL + `/room?num=` + tempSource.lroom;
                    };
                    jQ("#globalLoad").css({
                        display: 'none',
                        backgroundImage: `url('${cssUrl}loadBack.png')`
                    });
                })
                break;
            case "E":
            default:
                jQ("#globalLoad").css({
                    display: 'none',
                    backgroundImage: `url('${cssUrl}loadBack.png')`
                });
                break;
        }
    })

}
