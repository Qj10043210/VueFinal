import { BASE_URL } from "./url.js";
import * as ld from "./load.js";

var cUrl = BASE_URL + "/client/"
var loginUrl = cUrl + "login";
var signinUrl = cUrl + "signin";
var signupUrl = cUrl + "signup";
var tocharUrl = cUrl + "tochar";

var hUrl = jsUrl + "hair.json";
var fUrl = jsUrl + "face.json";
var gUrl = jsUrl + "game.json";
var cssUrl = "/css/"

var hairMapSize = 0, faceMapSize = 0, hairMap, faceMap, hairList, faceList, currentHair = 1, currentFace = 1, dice_max = 27, dice_run = false;

const l_url = fUrl;
let facejson;
const l_url2 = hUrl;
let hairjson;
const l_url3 = gUrl;
let gamejson;

ld.jsonData((data1) => {
    facejson = data1;
    ld.jsonData((data2) => {
        hairjson = data2;
        ld.jsonData((data3) => {
            gamejson = data3;

            const uniqueFaceNames = new Set();
            const uniqueHairNames = new Set();
            const uniqueGameNames = new Set();
            const uniqueFaceMaps = new Map();
            const uniqueHairMaps = new Map();
            const uniqueGameMaps = new Map();
            gamejson.forEach((item) => {
                let { g_title } = item;
                if (!uniqueGameNames.has(g_title)) {
                    uniqueGameNames.add(g_title);
                    uniqueGameMaps.set(g_title, item);

                }
            })
            facejson.forEach((item) => {
                let { face_name } = item;
                if (!uniqueFaceNames.has(face_name)) {
                    uniqueFaceNames.add(face_name);
                    uniqueFaceMaps.set(face_name, item);
                }
            })
            hairjson.forEach((item) => {
                let { hair_name } = item;
                if (!uniqueHairNames.has(hair_name)) {
                    uniqueHairNames.add(hair_name);
                    uniqueHairMaps.set(hair_name, item);
                }
            })
            gameMapSize += uniqueGameMaps.size;
            hairMapSize += uniqueHairMaps.size;
            faceMapSize += uniqueFaceMaps.size;
            jQ('#io_making_right_hair').val([...uniqueHairNames][0])
            jQ('#io_making_right_face').val([...uniqueFaceNames][0])
            jQ('#io_making_right_hair').data("hairSize", hairMapSize);
            jQ('#io_making_right_face').data("faceSize", faceMapSize);
            hairMap = uniqueHairMaps;
            faceMap = uniqueFaceMaps;
            hairList = uniqueHairNames;
            faceList = uniqueFaceNames;            
            
        }, l_url3)
    }, l_url2)
}, l_url);

console.log(faceList);