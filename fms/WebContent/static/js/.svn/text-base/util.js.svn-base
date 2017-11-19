/**
	 * totalpages	
	 */
	function showPagebar(showselect){
		//显示多少页数
		var showPageNumers = 10;
		var pageindex = parseInt($(showselect).attr("pageindex"));
		var totalpages = parseInt($(showselect).attr("totalpages"));
		var pagesize = parseInt($(showselect).attr("pagesize"));
		var total = parseInt($(showselect).attr("total"));
		var pageurl =$(showselect).attr("pageurl");
		var startPage = 0;
		var endPage = parseInt(totalpages);
		if(pageindex>totalpages)		pageindex=totalpages;
		if(pageindex<0)					pageindex=0;
				
		if(totalpages>showPageNumers){
			startPage = pageindex-(showPageNumers/2);
			endPage = pageindex+parseInt(showPageNumers/2);
		}
		if(startPage<0)	startPage = 0;
		if(endPage<showPageNumers)	endPage=showPageNumers;
		if(endPage>totalpages)	endPage=totalpages;
		
		$(showselect).html("<ul></ul>");
		if(pageindex>0){
			var url = regReplace(pageurl,"{pageindex}",(parseInt(pageindex)-1));
			$(showselect+" ul").append("<li><a href='"+url+"'>上一页</a></li>");
		}
		if(startPage>0){
			$(showselect+" ul").append("<li><a>...</a></li>");
		}
		for(var i=startPage;i<endPage; i++){
			var url = regReplace(pageurl,"{pageindex}",i);
			if(i==pageindex){
				$(showselect+" ul").append("<li class='active'><a href='"+url+"'>"+(i+1)+"</a></li>");
			}else{
				$(showselect+" ul").append("<li><a href='"+url+"'>"+(i+1)+"</a></li>");
			}
		}
		if(endPage<totalpages){
			$(showselect+" ul").append("<li><a>...</a></li>");
		}
		if((pageindex+1) < totalpages){
			var url = regReplace(pageurl,"{pageindex}",(pageindex+1));
			$(showselect+" ul").append("<li><a href='"+url+"'>下一页</a></li>");
		}
		$(showselect +" ul").append("<li class='active'><a href='#'>查询出"+total+"条数据，分为"+totalpages+"页显示</a></li>");
	}
	
	function regReplace(str,subtr,repsubstr){
		var reg=new RegExp(subtr,"g"); //创建正则RegExp对象
		var newstr=str.replace(reg,repsubstr); 
		return newstr;
	}