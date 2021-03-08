package prog12;

import java.util.*;

public class Tinge implements SearchEngine{
	HardDisk<PageFile> pageFiles = new HardDisk<PageFile>();
	PageTrie url2index = new PageTrie();
	
	Long indexPage(String url) {
		Long index = pageFiles.newFile();
		PageFile pageFile = new PageFile(index, url);
		System.out.println("indexing page" + pageFile);
		pageFiles.put(index, pageFile);
		url2index.put(url, index);
		
		return index;
	}

	@Override
	public void gather(Browser browser, List<String> startingURLs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] search(List<String> keyWords, int numResults) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
