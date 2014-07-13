
/**
 * 
 */
$(function(){
	var rootURL = "http://localhost:8083/ProjeGorev/rest/kullanicilar";
	var currentUser;
	//Kullanýcý listesi dolduruluyor
	kullaniciBul();
	$("button#btnKullaniciSil").hide();
	$("button#btnKullaniciGuncelle").hide();
	
	$("button#btnGiris").click(function(){
		kullaniciGirisi();
	});
	
	$("button#btnKullaniciKayit").click(function(){
		kullaniciKayit();
	});
	
	$("button#btnKullaniciGuncelle").click(function(){
		kullaniciGuncelle();
	});
	
	$("button#btnKullaniciSil").click(function(){
		kullaniciSil();
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
		kullaniciBulID($(this).data("identity"));
		$("button#btnKullaniciSil").show();
		$("button#btnKullaniciGuncelle").show();
		$("button#btnKullaniciKayit").hide();
		
		return false;
	});
	
	function kullaniciGuncelle(){
		var email = $("input[name=email]").val();
		var sifre = $('input[name=sifre]').val();
		var sifreTk = $('input[name=sifreTk]').val();
		var adi = $('input[name=adi]').val();
		var soyadi = $('input[name=soyadi]').val();
		
		if(!email && !sifre && !sifreTk && !adi && !soyadi){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Lütfen boþ alan býrakmayýnýz");
		}else if(!email.match(/\w+@\w+\.\w{2,4}/)){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Lütfen doðru bir E-Posta adresi giriniz");
		}else if(!sifre.match(sifreTk)){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Þifreleriniz uyuþmuyor");
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
					kullaniciBul();
				},
				error: function(data,textStatus,jqXHR){
					$("table tr td.bilgi").css("background","#f5000").text("Hata Oluþtu!");
				}
			});
		}
	}
	
	function kullaniciSil(){
		$.ajax({
			type:"DELETE",
			url:rootURL + '/' + $("input[name=id]").val(),
			dataType:"json",
			contentType:"application/json",
			data:kayitFormToJSON(),
			success:function(data){
				console.log("Kullanýcý Kaydý Silindi");
				$("table tr td.bilgi").css("background","lightgreen")
									  .text("Silme iþlemi baþarýlý")
									  .slideDown();
				$("table tr td.bilgi").slideUp(1000);
				kullaniciBul();
				user = {};
				kayitFormDoldur(user);
			},
			error: function(data,textStatus,jqXHR){
				$("table tr td.bilgi").css("background","#f5000").text("Hata Oluþtu!");
			}
		});
	}
	
	function kullaniciKayit(){
		var email = $("input[name=email]").val();
		var sifre = $('input[name=sifre]').val();
		var sifreTk = $('input[name=sifreTk]').val();
		var adi = $('input[name=adi]').val();
		var soyadi = $('input[name=soyadi]').val();
		
		if(!email && !sifre && !sifreTk && !adi && !soyadi){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Lütfen boþ alan býrakmayýnýz");
		}else if(!email.match(/\w+@\w+\.\w{2,4}/)){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Lütfen doðru bir E-Posta adresi giriniz");
		}else if(!sifre.match(sifreTk)){
			$("table tr td.bilgi").css("color","#ff0000").text("HATA! Þifreleriniz uyuþmuyor");
		}else{
			//Ýþlem yap
			$("input[name=id]").val("0");
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
					kullaniciBul();
				},
				error: function(data,textStatus,jqXHR){
					$("table tr td.bilgi").css("background","#f5000").text("Hata Oluþtu!");
				}
			});
		}
	}
	
	function kullaniciBulID(id){
		$.ajax({
			type:'GET',
			url:rootURL+'/'+id,
			dataType:'json',
			success:function(data){
				currentUser = data;
				kayitFormDoldur(currentUser);
			}
		});
	}
	
	function kullaniciBul(){
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
		$("table.liste").append('<tr class="head"><td>Adý</td><td>Soyadý</td><td>E-Posta</td></tr>');
		$.each(liste, function(index,user){
			$("table.liste").append('<tr class="data" data-identity="'+user.id+'">'+
					'<td>'+user.adi+'</td><td>'+user.soyadi+'</td><td>'+user.email+'</td></tr>');
		});
		$("table.liste tr:even").removeClass();
		$("table.liste tr:first").addClass("head");
	}
	
	function kayitFormDoldur(user){
		$("input[name=id]").val(user.id);
		$("input[name=adi]").val(user.adi);
		$("input[name=soyadi]").val(user.soyadi);
		$("input[name=email]").val(user.email);
	}
	
	function kayitFormToJSON(){
		return JSON.stringify({
			"id"    : $("input[name=id]").val(),
			"adi"    : $("input[name=adi]").val(),
			"soyadi" : $("input[name=soyadi]").val(),
			"email"  : $("input[name=email]").val(),
			"sifre"  : $("input[name=sifre]").val()
		});
	}
	
	/*
	function kullaniciGirisi(){
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
						$("table tr td.hata").css("color","red").text("Hata! Kullanici adi veya sifreniz yanlis");
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