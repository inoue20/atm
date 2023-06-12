/* ↓ 口座番号の取り扱いのためのヒント */
let accountId;



$(function(){
    $(".menuItem").on("click", function(){
        switchingShowContent(this);
    });

    $("#accountOpning").on("click", function(){
    	$.ajax({
    		url: "/bankTrading/open",
    		type: "POST",
    		ContentType: "application/json",
    	})
    	.done(function(response) {
    		$("#accountOpningErea").addClass("none");
            $("#accountMenu").removeClass("none");
    		accountId = response.id;
    	})

    });


    $("#checkBalance").on("click", function() {
    	$.ajax({
    		url: "/bankTrading/"+ accountId,
    		type: "GET",
    		ContentType: "application/json",
    	})

    	})


    	$(".btn-primary1").on("click", function() {
	    	const data = {
	    		amount: $("#deposit2").val()
	    	}

    	$.ajax({
    		url: "/bankTrading/deposit/" + accountId,
    		type: "POST",
    		ContentType: "application/json",
    		data: JSON.stringify(data),
    	})
    	.done(function(response) {
    		checkBalanceRewriting(response, amount)
    	})
})


$(".btn-primary2").on("click", function() {
	    	const data = {
	    			amount: $("withdrawal2").val()
	    	}
	    	$.ajax({
	    		url: "/bankTrading/withdraw/" + accountId,
	    		type: "POST",
	    		ContentType: "application/json",
	    		data: JSON.stringify(data),
	    	})
	    	.done(function(response) {
    		checkBalanceRewriting(response, amount)
    	})
    })


});


function checkBalanceRewriting(amount) {
    $("#checkBalanceAmount").text(amount);
}


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