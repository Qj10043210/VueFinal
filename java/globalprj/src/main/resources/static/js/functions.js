export function functions_RandomStartEndSize(x, y, z) {
    let a = Math.random()
    let b = Math.floor(a * z);
    let c = true;
    while (c) {
        if (b >= x && b <= y) {
            c = false;
        }
        else {
            a = Math.random()
            b = Math.floor(a * z);
        }
    }
    return b;
}


export function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
      var j = Math.floor(Math.random() * (i + 1));
      // Swap array[i] and array[j]
      var temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }
  
  
  export function findAndMakeMap(targets, targetvalue, map){
    let tt = (targets).toString();
    let targetvalues = (targetvalue).toString();
    for( let[key, value] of map){    	
        if(value[tt] == targetvalues){
            return [key, value];
        }
    }
    return null;
}