export function readySituation(callback, sources, urls){
	jQ.ajax({
	
		url : urls + "readySitu",
		type : 'POST',
			contentType: 'application/json',
	        data: JSON.stringify(sources),
	        success: function (data) {
	        	callback(data)
	        },
	        error: function (errorThrown) {
	            
	        }
	
	})

}

export function updateReady(callback, sources, urls){
	jQ.ajax({
		url : urls + "updateReady",
		type : 'POST',
			contentType: 'application/json',
	        data: JSON.stringify(sources),
	        success: function (data) {
	        	callback(data)
	        },
	        error: function (errorThrown) {
	            
	        }
	})
}