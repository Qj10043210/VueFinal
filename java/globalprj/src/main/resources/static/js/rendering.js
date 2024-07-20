export const g_bc_w = 6000, 
g_bc_h = 4500, 
g_bc_x = (g_bc_w / 2), 
g_bc_y = (g_bc_h / 2), 
g_bc_col = "wheat",
g_bc_url="./css/back/logo_frame.png",
g_bc_level=1,
g_bc_monsters=300,
g_bc_monsterSize=60;
//  temp
//  get Background value
//
//
export const g_speed = 30;
export const g_objsize = 100;
export const g_objqty = 30;
export const g_bc = document.getElementById('g_bc');
export const g_sc = document.getElementById('g_sc');
g_bc.style.width = `${g_bc_w}px`;
g_bc.style.height = `${g_bc_h}px`;
g_bc.style.backgroundColor = `${g_bc_col}`;
g_bc.style.backgroundImage = `url('${g_bc_url}')`
g_bc.style.top = `-${g_bc_y}px`;
g_bc.style.left = `-${g_bc_x}px`;

export const g_values = {
    g_speed : g_speed,
    g_bc : g_bc,
    g_sc : g_sc,
    g_bc_w : g_bc_w,
    g_bc_h : g_bc_h,
    g_bc_x : g_bc_x,
    g_bc_y : g_bc_y
}

export var g_matrix_x = [];
export var g_matrix_y = [];
makeMatrix(g_bc_w, g_objsize)
makeMatrix(g_bc_h, g_objsize)

function makeMatrix(boxsize, objsize){
    let target_qty = parseInt(boxsize / objsize);
    for(let i = 0; i<target_qty-1; i++){
        
        if(boxsize==g_bc_w){//width
            g_matrix_x.push(i)
        }else{
            g_matrix_y.push(i)
        }
    }

    
}
