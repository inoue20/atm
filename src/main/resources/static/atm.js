/* ↓ 口座番号の取り扱いのためのヒント */
let accountId;

let amount;

$(function(){
    $(".menuItem").on("click", function(){
        switchingShowContent(this);
    });

    $("#accountOpning").on("click", function(){
        $("#accountOpningErea").addClass("none");
        $("#accountMenu").removeClass("none");
    });


});


$(function() {
	    $(".btn").on("click", function() {
    	$.ajax({
    		url: "/bankTrading/open",
    		type: "POST",
    	})
    	.done(function(response) {
    		accountId = response.accountId;
    	})
    })
})


$(function() {
	    $("#checkBalance").on("click", function() {
    	$.ajax({
    		url: "/bankTrading/"+ accountId,
    		type: "GET",
    	})

    	})
    })
})


$(function() {
	    $(".btn-primary1").on("click", function() {
	    	amount = $("#deposit").val()
	    	console.log(amount)
//    	$.ajax({
//    		url: "/bankTrading/deposit/" + accountId,
//    		type: "POST",
//    		ContentType: "application/json",
//    		data: amount,
//    	})
//    	.done(function(response) {
//    		amount = response.requestAmount;
//    })
})
})


$(function() {
	    $(".btn-primary2").on("click", function() {
    	$.ajax({
    		url: "/bankTrading/withdraw/" + accountId,
    		type: "POST",
    	})
    })
})



$(document).ready(function() {
	$.get("/" + accountId, function(response) {
		$("#amountSpan").text(response.amount);
	})
})

// メニューを選択した時の画面表示の切り替え
// 引数はクリックされたDOM自身が入ってくるのを想定しておく
function switchingShowContent(targetElement)
{
    classInit();
    // id名を自身から取得して、removeClassする対象を決定する
    var idName = $(targetElement).attr("id");
    $("#" + idName + "Erea").removeClass("none");
}

// classがあるので、それを利用して一括でcss反映
function classInit(){
    $(".content").addClass("none");
}