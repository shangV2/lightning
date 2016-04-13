package com.lightning.datacenter.importer;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightning.datacenter.utils.FileIOUtility;

public abstract class QFImporter {
	
//	public void init();
	
	private static final Logger logger = LoggerFactory.getLogger("importer");

	public void process() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Date llag = calendar.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timestamp = sdf.format(llag);
		
		File file = new File(getRecordFilePath());
		
		Date now = null;
		int trytimes = 0;
		do {
			trytimes ++;
			if ( !file.exists() ) {
				FileIOUtility.writeContent(file.getAbsolutePath(), timestamp);
			}
			
			String recordTimestamp = FileIOUtility.readContent(file.getAbsolutePath());
			
			if ( recordTimestamp == null || recordTimestamp.trim().length() == 0 ) {
				FileIOUtility.writeContent(file.getAbsolutePath(), timestamp);
				continue;
			}
			
			try {
				now = sdf.parse(recordTimestamp);
			} catch (Exception e) {
				logger.warn("sensitive import fail for reason that => record file value is not valid number");
			}
			
			if ( now != null ) {
				break;
			}
		} while (trytimes < 3);
		
		if ( now != null ) {
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(Calendar.HOUR_OF_DAY, 0);
			calendar2.set(Calendar.MINUTE, 0);
			calendar2.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.MILLISECOND, 0);
			Date cutoff = calendar2.getTime();
			while ( now.compareTo(cutoff) < 0 ) {
				List<String> filepaths = extract(new File(getDataDirPath(), sdf.format(now)).getAbsolutePath());
				
				logger.debug(
						String.format("timestamp: %s, extract from path: %s, content_files: %d", 
						sdf.format(now), getDataDirPath(), filepaths.size())
					);
				
				SuperFileQueue sfq = new SuperFileQueue(filepaths);
				handle(sdf.format(now), sfq);
				
				Calendar inc = Calendar.getInstance();
				inc.setTime(now);
				inc.add(Calendar.DAY_OF_MONTH, 1);
				now = inc.getTime();
				
				FileIOUtility.writeContent(getRecordFilePath(), sdf.format(now));
			}
		}
		
	}
	
	private List<String> extract(String path) {
		
		List<String> results = new ArrayList<String>();
		
		File dir = new File(path);
		
		if ( !dir.exists() ) {
			logger.warn(
					String.format("extract path: %s, but not exits", path)
				);
			return results;
		}
		
		String[] filenames = dir.list(new FilenameFilter() {
			Pattern FMQ_PATTERN = Pattern.compile(".*\\.fmq");
			@Override
			public boolean accept(File dir, String filename) {
				return FMQ_PATTERN.matcher(filename).matches() || filename.endsWith(".fmq");
			}
		});
		
		logger.debug(
				String.format("extract path: %s, filenames: %d", path, filenames.length)
			);
		
		for ( String filename : filenames ) {
			results.add(new File(dir, filename).getAbsolutePath());
		}
		
		return results;
		
	}
	
	public abstract String getDataDirPath();
	
	public abstract String getRecordFilePath();
	
	public abstract void handle(String timestamp, SuperFileQueue sfq);
	
}
