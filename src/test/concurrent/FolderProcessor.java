package test.concurrent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private String extension;
	
	public FolderProcessor(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
		File file = new File(path);
		File[] content = file.listFiles();
		if(content != null){
			for(int i = 0; i < content.length; i++){
				if(content[i].isDirectory()){
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					task.fork();
					tasks.add(task);
				}else{
					if(checkFile(content[i].getName())){
						list.add(content[i].getAbsolutePath());
					}
				}
			}
			if(tasks.size() > 50){
				System.out.printf("%s: %d tasks ran.", file.getAbsolutePath(), tasks.size());
			}
			addResultsFromTasks(list, tasks);
		}
		return list;
	}

	private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks){
		for(FolderProcessor task : tasks){
			list.addAll(task.join());
		}
	}
	
	private boolean checkFile(String name){
		return name.endsWith(extension);
	}
}
