$('#summernote').summernote({
  placeholder: '',
  tabsize: 2,
  height: 200
});

$(document).ready(function(){
    $("form").submit(function(even){
        var content = $(".note-editable").html();
        $("#txtContent").attr("value", content);
//        even.preventDefault();
    });
    
    var oldContent = $("#txtContent").attr("value");
    $(".note-editable").html(oldContent);
})
