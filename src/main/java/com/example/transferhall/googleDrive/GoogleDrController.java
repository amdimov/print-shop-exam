package com.example.transferhall.googleDrive;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
public class GoogleDrController {
    private final DriveQuickstart driveQuickstart;
    private final DriveRepository driveRepository;
    private static final String GOOGLE_DRIVE_ROOT = "https://drive.google.com/uc?export=view&id=";
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDrController.class);

    public GoogleDrController(DriveQuickstart driveQuickstart, DriveRepository driveRepository) {
        this.driveQuickstart = driveQuickstart;
        this.driveRepository = driveRepository;
    }

    @GetMapping("/test-gd")
    public String testGD(){
        try {
            Drive drive = driveQuickstart.getInstance();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "tests/test_gd";
    }
    @GetMapping("/test-upload-drive")
    public String testUploadToDrive(){
        try {
            Drive drive = driveQuickstart.getInstance();
            //uploadImageTest(drive);
//            downloadFileToServer("1GWaUgbfV-RWqX5Qc9tNHS8xNfU1qXGgQ", drive);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "tests/test_gd";
    }

    @PostMapping("/test-upload-drive")
    public String uploadFile(@RequestParam MultipartFile fileToDrive){
        try {
            Drive drive = driveQuickstart.getInstance();
            upload(drive, fileToDrive);
            //uploadImageTest(drive);
//            downloadFileToServer("1GWaUgbfV-RWqX5Qc9tNHS8xNfU1qXGgQ", drive);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "tests/test_gd";
    }
    @GetMapping("/test-upload-drive/display")
    public String displayImages(Model model){
        try {
            Drive drive = driveQuickstart.getInstance();
            List<DriveEntity> driveImages = driveRepository.findAll();
            model.addAttribute("images", driveImages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "tests/test_gd_display";
    }

    @Transactional
    @DeleteMapping("/test-upload-drive/display/delete")
    public String deleteImage(@RequestParam String publicId){
        try {
            Drive drive = driveQuickstart.getInstance();
            drive.files().delete(publicId).execute();
            driveRepository.deleteAllByPublicId(publicId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/test-upload-drive/display";
    }


    private void uploadImageTest(Drive drive) throws Exception {
        File fileMetadata = new File();
        fileMetadata.setName("quick.png");

        java.io.File filePath = new java.io.File("D:\\Alex\\SpringProjects\\transferhall\\src\\main\\resources\\static\\uploads\\quick.png");
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        File file = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println(fileMetadata.getId());
        System.out.println(fileMetadata.getWebViewLink());
        System.out.println("---");
        System.out.println(file.getId());
        System.out.println(file.getWebViewLink());
        System.out.println(file.getWebContentLink());
//        System.out.println("File ID: " + file.getId());
    }
    private void upload(Drive drive, MultipartFile multipartFile) throws Exception {
        File fileMetadata = new File();
        fileMetadata.setName(multipartFile.getOriginalFilename());
        java.io.File tempFile =
                java.io.File.createTempFile("temp-name", multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        FileContent mediaContent = new FileContent("image/jpeg", tempFile);
        File file = drive.files().create(fileMetadata, mediaContent).setFields("id").execute();
        DriveEntity driveEntity = new DriveEntity().setPublicId(file.getId())
                .setUrl(GOOGLE_DRIVE_ROOT+file.getId());
//        Permission permission = new Permission();
//        permission.setType("anyone")
//                .setRole("reader");
//        drive.permissions().create(file.getId(), permission);
        //drive.files().get(file.getId()).setFields("webViewLink");
        LOGGER.info(file.getWebViewLink());
        driveRepository.save(driveEntity);
        tempFile.delete();
    }


    private void downloadFileToServer(String fileId, Drive drive){
        //1GWaUgbfV-RWqX5Qc9tNHS8xNfU1qXGgQ
        try {

            OutputStream outputStream =
                    new FileOutputStream("D:\\Alex\\SpringProjects\\transferhall\\src\\main\\resources\\static\\uploads\\TestPdfFile.pdf");
            drive.files().get(fileId)
                    .executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
