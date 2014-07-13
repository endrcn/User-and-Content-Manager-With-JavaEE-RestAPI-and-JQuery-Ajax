
/**
 * 
 */
$(function(){
	var rootURL = "http://localhost:8083/ProjeGorev/rest/icerik";
	var currentContent;
	//Ýçerik listesi dolduruluyor
	icerikBul();
	$("button#btnIcerikSil").hide();
	$("button#btnIcerikGuncelle").hide();
	
	$("button#btnGiris").click(function(){
		icerikGirisi();
	});
	
	$("button#btnIcerikKayit").click(function(){
		icerikKayit();
	});
	
	$("button#btnIcerikGuncelle").click(function(){
		icerikGuncelle();
	});
	
	$("button#btnIcerikSil").click(function(){
		icerikSil();
	});
	
	$(document).on('mouseover','table.liste tr:not(:first)',function(e){
		$(this).addClass("hover");
	});
	
	$(document).on('mouseleave','table.liste tr:not(:first)',function(e){
		$("table.liste tr").removeClass();
		$("table.liste tr:odd").addClass("data");
		$("table.liste tr:first").addClass("head");
	});
	
	$(document).on('click','table.liste tr:not(:first)',function(){
		icerikBulID($(this).data("identity"));
		$("button#btnIcerikSil").show();
		$("button#btnIcerikGuncelle").show();
		$("button#btnIcerikKayit").hide();
		
		return false;
	});
	
	function icerikGuncelle(){
		var baslik = $("input[name=baslik]").val();
		var detay = $('textarea#detay').val();
		
		if(!baslik && !detay){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Lütfen boþ alan býrakmayýnýz");
		}else{
			//Ýþlem yap
			$.ajax({
				type:"PUT",
				url:rootURL + '/' + $("input[name=id]").val(),
				dataType:"json",
				contentType:"application/json",
				data:kayitFormToJSON(),
				success:function(data){
					console.log("Güncelleme baþarýlý");
					$("table tr td.bilgi").css("background","lightgreen")
										  .text("Güncelleme Baþarýlý")
										  .slideDown();
					$("table tr td.bilgi").slideUp(1000);
					icerikBul();
				},
				error: function(data,textStatus,jqXHR){
					$("table tr td.bilgi").css("background","#f5000").text("Hata Oluþtu!");
				}
			});
		}
	}
	
	function icerikSil(){
		$.ajax({
			type:"DELETE",
			url:rootURL + '/' + $("input[name=id]").val(),
			dataType:"json",
			contentType:"application/json",
			data:kayitFormToJSON(),
			success:function(data){
				console.log("Ýçerik Kaydý Silindi");
				$("table tr td.bilgi").css("background","lightgreen")
									  .text("Silme iþlemi baþarýlý")
									  .slideDown();
				$("table tr td.bilgi").slideUp(1000);
				icerikBul();
				icerik = {};
				kayitFormDoldur(icerik);
			},
			error: function(data,textStatus,jqXHR){
				$("table tr td.bilgi").css("background","#f5000").text("Hata Oluþtu!");
			}
		});
	}
	
	function icerikKayit(){
		var baslik = $("input[name=baslik]").val();
		var detay = $('textarea#detay').val();
		
		if(!baslik && !detay){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Lütfen boþ alan býrakmayýnýz");
		}else{
			//Ýþlem yap
			$("input[name=id]").val("0");
			$("input[name=resim").val(($("input[name=resim").val()!="")? $("input[name=resim").val():"");
			$.ajax({
				type:"POST",
				url:rootURL,
				dataType:"json",
				contentType:"application/json",
				data:kayitFormToJSON(),
				success:function(data){
					console.log("Kayýt baþarýlý");
					$("table tr td.bilgi").css("background","lightgreen")
										  .text("Kayýt Baþarýlý")
										  .slideDown();
					$("table tr td.bilgi").slideUp(1000);
					icerikBul();
				},
				error: function(data,textStatus,jqXHR){
					$("table tr td.bilgi").css("background","#f5000").text("Hata Oluþtu!");
				}
			});
		}
	}
	
	function icerikBulID(id){
		$.ajax({
			type:'GET',
			url:rootURL+'/'+id,
			dataType:'json',
			success:function(data){
				currentContent = data;
				kayitFormDoldur(currentContent);
			}
		});
	}
	
	function icerikBul(){
		$.ajax({
			type: 'GET',
			url: rootURL,
			dataType: "json", // data type of response
			success: listeDoldur
		});
		
	}
	
	function listeDoldur(data){
		console.log("liste doldur");
		var liste = data==null? [] : (data instanceof Array ? data : [data]);
		$("table.liste tr").remove();
		$("table.liste").append('<tr class="head"><td>Baþlýk</td><td>Resim</td>');
		$.each(liste, function(index,icerik){
			$("table.liste").append('<tr class="data" data-identity="'+icerik.id+'">'+
					'<td>'+icerik.baslik+'</td><td>'+icerik.resim+'</td></tr>');
		});
		$("table.liste tr:even").removeClass();
		$("table.liste tr:first").addClass("head");
	}
	
	function kayitFormDoldur(icerik){
		$("input[name=id]").val(icerik.id);
		$("input[name=baslik]").val(icerik.baslik);
		$("textarea#detay").val(icerik.detay);
	}
	
	function kayitFormToJSON(){
		return JSON.stringify({
			"id"    : $("input[name=id]").val(),
			"baslik"    : $("input[name=baslik]").val(),
			"detay" : $("textarea#detay").val(),
			"resim"  : $("input[name=resim]").val()
		});
	}
	
	/*
	function icerikGirisi(){
		var email = $("input[name=email]").val();
		var sifre = $('input[name=sifre]').val();
		if(email.match(/\w+@\w+\.\w{2,4}/)){
			$.ajax({
				type: 'POST',
				url: rootURL,
				dataType: "json",
				contentType:"application/json",
				data:girisFormToJSON(),
				success: function(data){
					if(data!=null){
						window.location.href = "signup.html"
					}else{
						$("table tr td.hata").css("color","red").text("Hata! Icerik adi veya sifreniz yanlis");
					}
					console.log('findById success: ' + data.adi);
					//$("table tr td.hata").text("Giris basarili : "+data.adi);
				},
				error:function(data,textStatus,jqXHR){
					$("table tr td.hata").css("color","red").text("Hata! "+textStatus);
				}
			});
		}else{
			$("table tr td.hata").css("color","red").text("Hata! Lutfen dogru bir E-Posta adresi giriniz");
		}
	}
	
	function girisFormToJSON(){
		return JSON.stringify({
			"email" : $("input[name=email]").val(),
			"sifre" : $("input[name=sifre]").val()
		});
	}
	*/
	
});