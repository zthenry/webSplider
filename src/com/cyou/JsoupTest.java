/*
 * 文 件 名:  JsoupTest.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-5-9
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 获取百度手机助手游戏信息
 * 每个游戏的信息组织结构如下，主要完成对如下结构的解析，获取必要信息
 * <div class="app-box"> 

 <div class="app-intro"> 

  <div class="app-icon"> 
   <a href="/game/item?docid=5397375&amp;from=&amp;f=list_game_404%40sort_popular_0%401"> <img src="http://b.hiphotos.bdimg.com/wisegame/pic/item/b209b3de9c82d1589dfb9fd5820a19d8bc3e425d.jpg" alt="烧饼修改器" width="68" height="68" /> 
    <div class="mask mask1"></div> </a> 
  </div> 

  <div class="app-meta"> 
   <p class="name"> <a href="/game/item?docid=5397375&amp;from=&amp;f=list_game_404%40sort_popular_0%401">烧饼修改器</a> </p> 
   <p class="star-wrap"><span class="star"><span style="width:84%"></span></span></p> 
   <p class="down-num">1000万+</p> 
  </div> 

 </div> 


 <div class="app-detail"> 
  <a class="link-wrap" href="/game/item?docid=5397375&amp;from=&amp;f=list_game_404%40sort_popular_0%401"> <h3 class="name">烧饼修改器</h3> <p class="star-wrap"><span class="star"><span style="width:84%"></span></span></p> <p class="description"> 烧饼，嗯，这个有点意思，游戏玩家适当的修改下游戏，… </p> </a> 
  <div class="inst-wrap"> 
   <a class="inst-btn" href="javascript:;" onclick="bd_app_dl(this,event);" data_type="apk" data_detail_type="game" data_url="http://gdown.baidu.com/data/wisegame/ead4b673801d2b9b/GameHacker_51.apk" data_name="烧饼修改器" data_package="org.sbtools.gamehack" data_versionname="2.6.3" data_icon="http://b.hiphotos.bdimg.com/wisegame/pic/item/b209b3de9c82d1589dfb9fd5820a19d8bc3e425d.jpg" data_size="999011" data-action="list_game_404" data-mod="sort_popular_0" data-pos="1" data-tj="game_5397375_253409_烧饼修改器"> <em>安装</em> </a> 
  </div> 
 </div> 

</div>
 * @author Administrator
 * @version [版本号, 2014-5-9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JsoupTest {

  /**
   *demo,测试代码
   * 
   * @param args
   * @see [类、类#方法、类#成员]
   */
  public static void main(String[] args) {
	  //TODO 需要接口化，以及配置化一些东西
    Map<String, String> urlMap = new HashMap<String, String>();
    try {
      //百度手机助手url前缀
      String baiduZhuShouBaseUrl = "http://shouji.baidu.com";
      //http://shouji.baidu.com/game/list?cid=401&from=&f=game%40catetag%402&page_num=1
      
      //游戏页url
      String urlPrefix="http://shouji.baidu.com/game";
      String html = Jsoup.connect(urlPrefix).execute().body();
      Document doc = Jsoup.parse(html);
      
      //获取游戏热门分类
      Elements elements = doc.getElementsByClass("hot-cate");
      
      //获取所有游戏分类以及对应的url
      Elements catetags = elements.select("li");
      for(int i = 1; i < catetags.size(); i++) {
        String url = catetags.get(i).getElementsByTag("a").attr("href");
        String text = catetags.get(i).getElementsByTag("a").text();
        //分类名称以及url保存到Map中
        urlMap.put(text,url);
      }
      
      //遍历所有分类的url
      for (String key : urlMap.keySet()) {
        String subUrl = urlMap.get(key);
        String pageOrder = "&page_num=";
        //完整的url
        String url = baiduZhuShouBaseUrl + subUrl+pageOrder;
        for (int i = 0; i < 100; i++) {
        	String tempUrl = url + i;
        	String gamePage = Jsoup.connect(tempUrl).execute().body();
            Document gameDoc = Jsoup.parse(gamePage);
            
            //当前页游戏信息
            Elements  allGamesPerPage = gameDoc.getElementsByClass("app-box");
            if (null==allGamesPerPage || allGamesPerPage.size()==0) {
    			break;
    		}
            else {
            	System.out.println("==================begin===page"+i+"========================");
            	//System.out.println(allGamesPerPage);	
            	//System.out.println(allGamesPerPage.size());
            	for (Element element : allGamesPerPage) {
            		System.out.println("==================every game info start=======================");
            		Elements appIconInfo = element.getElementsByClass("app-icon");
            		String gameIconUrl = appIconInfo.get(0).getElementsByTag("img").attr("src");
            		Elements appMeta = element.getElementsByClass("app-meta");
            		String gameSubfixUrl = appMeta.get(0).getElementsByTag("a").attr("href");
            		String gameName = appMeta.get(0).getElementsByTag("a").text();
            		String gameUrl = baiduZhuShouBaseUrl+gameSubfixUrl;
            		System.out.println("游戏名称:"+gameName);
            		System.out.println("游戏url:"+gameUrl);
            		System.out.println("游戏图标url:"+gameIconUrl);
            		Elements allAppDetails = element.getElementsByClass("app-detail");
            		Elements subAppDetails = element.getElementsByClass("inst-wrap");
            		String dataUrl = subAppDetails.get(0).getElementsByTag("a").attr("data_url");
            		String dataVersion = subAppDetails.get(0).getElementsByTag("a").attr("data_versionname");
            		
            		System.out.println("游戏下载地址:"+dataUrl+",游戏版本:"+dataVersion);
            		System.out.println("游戏版本:"+dataVersion);
            		System.out.println("==================every game info end=======================");
				}
              	System.out.println("==================end===page"+i+"========================");
    		}	
		}
        
       }
    }
    catch(Exception e) {
    }
   
  }
}
