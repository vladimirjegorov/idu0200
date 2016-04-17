$(document).ready(function () {
  var $progress = $("#progress");
  var $form = $("#form");

  $("#modal").on("show.bs.modal", function (e) {
    $progress.show();
    $form.hide();

    var link = $(e.relatedTarget);
    $.get(link.attr("href"), function (data) {
      $progress.hide();
      $form.show();

      $form.find("#id").text(data.id);
      $form.find("#content").text(data.content);
    });
  });
});