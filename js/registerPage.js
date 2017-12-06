function showDiv(modetratorRbt) {
  var exAndPlace = document.getElementById("ex-and-place");
  console.log(exAndPlace);
  if (modetratorRbt.checked)
    exAndPlace.style.display = "block";

}

function hideDiv(memberRbt) {
  var exAndPlace = document.getElementById("ex-and-place");
  console.log(exAndPlace);
  if (memberRbt.checked)
    exAndPlace.style.display = "none";

}
$(function () {
  $('.datepicker').datepicker({format: "dd//mm/yyyy"}).on('changeDate', function (ev) {
    $(this).datepicker('hide');
  });
});

