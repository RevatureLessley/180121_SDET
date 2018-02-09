/**
 * 
 */
$(document).ready(function(){
 
	// remove any error messages after 3 seconds
	setTimeout(function(){
		$(".alert").fadeOut("slow");
	} ,3000);
	
	
	
	//setup dataTables
	$(".pubhub-datatable").DataTable({
		stateSave: true,
		lengthChange: false,
		info:false,
		language: {
			zeroRecords: "No items matched your search"
		}
		
	});
	
    // Script to Activate the Carousel
    $('.carousel').carousel({
//        interval: 5000 //changes the speed
    });

	
//	$(".pubhub-simple-datatable").DataTable({
//		paging: false,
//		searching: false,
//		info: false,
//		ordering: false
//		
//	});	
});