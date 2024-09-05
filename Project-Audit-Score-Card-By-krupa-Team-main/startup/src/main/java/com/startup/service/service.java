package com.startup.service;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.servlet.http.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import com.startup.model.*;
import com.startup.repository.*;

public class service {

	@SuppressWarnings("deprecation")
	public static List<String> uploadfiletoservice(Part filePart, String tablename, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();
		String filePath = null;
		try {
			File targetFile = new File(request.getRealPath("/") + filename);
			Files.copy(fileContent, targetFile.toPath());
			filePath = targetFile.getPath();
		} catch (FileAlreadyExistsException ex) {
			ex.printStackTrace();
		}

		if (tablename.equals("all")) {
			try {
				startuptableservice(filePath);
				List<String> Sheetnames = repositroy.getnames();
				for (String name : Sheetnames) {
					try {
						saattableservice(filePath, name);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try {
						samanualtableservice(filePath, name);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try {
						sscardtableservice(filePath, name);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (tablename.equals("startup")) {
			try {
				startuptableservice(filePath);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (tablename.equals("saatracker")) {
			try {
				startuptableservice(filePath);
				List<String> Sheetnames = repositroy.getnames();
				for (String name : Sheetnames) {
					try {
						saattableservice(filePath, name);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (tablename.equals("samanual")) {
			try {
				startuptableservice(filePath);
				List<String> Sheetnames = repositroy.getnames();
				for (String name : Sheetnames) {
					try {
						samanualtableservice(filePath, name);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (tablename.equals("sscard")) {
			try {
				startuptableservice(filePath);
				List<String> Sheetnames = repositroy.getnames();
				for (String name : Sheetnames) {
					try {
						sscardtableservice(filePath, name);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		File file = new File(filePath);
		if (file.exists()) {
			Files.delete(Paths.get(filePath));
			try {
				File startupDir = new File(

						"C:\\Users\\prani\\eclipse-workspace-servlets\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\work\\Catalina\\localhost\\startup");
				File[] files = startupDir.listFiles();
				if (files != null) {
					for (File filetemp : files) {
						if (filetemp.getName().endsWith(".tmp")) {
							if (filetemp.delete()) {
								System.out.println("Deleted file: " + filetemp.getAbsolutePath());
							} else {
								System.out.println("Failed to delete file: " + filetemp.getAbsolutePath());
							}
						}
					}
				} else {
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return repositroy.getnames();
	}

	private static void sscardtableservice(String filePath, String name) {

		try {
			Workbook workbook = new XSSFWorkbook(new File(filePath));
			Sheet sheet = workbook.getSheet(name);
			// skip the first row from sheet
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			rows.next();
			long id = 1;
			List<sscard> list = new ArrayList<sscard>();
			String priviousprocessarea = "";

			while (rows.hasNext()) {
				Row row = rows.next();
				String startupname = name;
				String processarea = row.getCell(0).getStringCellValue();
				double actualscore = 0;
				String remark = null;

				if (processarea == priviousprocessarea) {
					continue;
				} else {
					priviousprocessarea = processarea;
				}

				sscard Sscard = new sscard(startupname, id++, row.getCell(7).getStringCellValue(),
						row.getCell(8).getStringCellValue(), processarea, row.getCell(3).getNumericCellValue(),
						row.getCell(4).getNumericCellValue(), actualscore, remark, new Date());
				System.out.println(Sscard.getStartupname() + " : " + Sscard.getId() + " : " + Sscard.getProcessarea()
						+ " : " + Sscard.getDimension() + " : " + Sscard.getPerspective() + Sscard.getProcessarea()
						+ " : " + Sscard.getMaxscore() + " : " + Sscard.getWeight() + Sscard.getActualscore() + " : "
						+ Sscard.getRemark());
				list.add(Sscard);
			}
			repositroy.savesscard(list);
			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void samanualtableservice(String filePath, String name) {
		try {
			Workbook workbook = new XSSFWorkbook(new File(filePath));
			Sheet sheet = workbook.getSheet(name);
			// skip the first row from sheet
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			rows.next();
			long id = 1;
			List<samanual> list = new ArrayList<samanual>();
			String priviousprocessarea = "";
			while (rows.hasNext()) {
				Row row = rows.next();
				String startupname = name;
				String processarea = row.getCell(0).getStringCellValue();
				// int number_of_links = (int) row.getCell(2).getNumericCellValue();
				double actualscore = 0;
				String remark = null;
				if (processarea == priviousprocessarea) {
					continue;
				} else {
					priviousprocessarea = processarea;
				}

				samanual Samanual = new samanual(startupname, id++, processarea,
						(int) row.getCell(2).getNumericCellValue(), actualscore, remark);
				System.out.println(Samanual.getStartupname() + " : " + Samanual.getId() + " : "
						+ Samanual.getProcessarea() + " : " + Samanual.getNumber_of_link() + " : "
						+ Samanual.getActualscore() + " : " + Samanual.getRemark());
				list.add(Samanual);
			}
			repositroy.savesamanual(list);
			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void saattableservice(String filePath, String name) {

		try {
			Workbook workbook = new XSSFWorkbook(new File(filePath));
			Sheet sheet = workbook.getSheet(name);
			// skip the first row from sheet
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			rows.next();
			long id = 1;
			List<saatracker> list = new ArrayList<saatracker>();
			while (rows.hasNext()) {
				Row row = rows.next();
				String startupname = name;
				String processarea = row.getCell(0).getStringCellValue();
				String linkURL = row.getCell(1).getStringCellValue();
				linkURL = linkURL.replaceAll("/url\\?q=", "");
				String status = "inserted";
				saatracker Saatracker = new saatracker(startupname, id++, processarea, linkURL, new Date(), status);
				list.add(Saatracker);
			}
			repositroy.savesaatracker(list);
			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void startuptableservice(String filePath) {
		try {
			Workbook workbook = new XSSFWorkbook(new File(filePath));
			Sheet sheet = workbook.getSheet("StartUp");
			// skip the first row from sheet
			Iterator<Row> rows = sheet.iterator();
			rows.next();
			while (rows.hasNext()) {
				Row row = rows.next();
				String startupCode = null;
				String startupCategory = null;
				int srNo = (int) row.getCell(0).getNumericCellValue();
				String startupName = row.getCell(1).getStringCellValue();
				String website = row.getCell(2).getStringCellValue();
				startup startup = new startup(startupCode, startupCategory, srNo, startupName, website);
				try {
					repositroy.savestartup(startup);
				} catch (Exception ex) {
				}
			}
			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static List<startup> getdata(String startupname) {
		return repositroy.getdata(startupname);
	}

	public static List<saatracker> getdata1(String startupname) {
		// TODO Auto-generated method stub
		return repositroy.getdata1(startupname);
	}

	public static List<samanual> getdata2(String startupname) {

		return repositroy.getdata2(startupname);
	}

	public static List<sscard> getdata3(String startupname) {
		// TODO Auto-generated method stub
		return repositroy.getdata3(startupname);
	}

	public static void update(startup startup) {
		repositroy.update(startup);

	}

	public static int deletedata(String name) {
		return repositroy.deletedata(name);
	}

	

	public static void updatemanualandcard(samanual one) {
		// TODO Auto-generated method stub
		repositroy.updatemanual(one);
	}

}
