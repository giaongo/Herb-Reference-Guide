package fi.metropolia.herbreferenceguide.camera;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import fi.metropolia.herbreferenceguide.*;

/**
 * This activity implements the multiple request permission of Camera and Media storage.
 * Displays camera preview with feature of taking, viewing photos and flipping the camera lens with
 * the utilisation of CameraX API
 *
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isReadPermissionGranted = false;
    private boolean isWritePermissionGranted = false;
    private boolean isCameraPermissionGranted = false;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private PreviewView previewView;
    private int lensFacing = CameraSelector.LENS_FACING_BACK;
    private ImageCapture imageCapture;
    public static final String LENS_FACING = "lens";

    /**
     * Launches activity for result. The permission result is recorded based on the permission result
     * received back from user's decision (either granted permission access or denied).
     * Code reference on the topic of request multiple permissions:
     * @see <a href="https://www.youtube.com/watch?v=nkayHRT8D_w&ab_channel=Foxandroid">
     * How to request multiple permissions</a>
     * <a href="https://developer.android.com/training/permissions/requesting">
     *  Android Studio: Request app permission</a>
     */
    ActivityResultLauncher<String[]> mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
            result -> {
                if (result.containsKey(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    isReadPermissionGranted = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
                if (result.containsKey(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    isWritePermissionGranted = result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (result.containsKey(Manifest.permission.CAMERA)) {
                    isCameraPermissionGranted = result.get(Manifest.permission.CAMERA);
                }
            });

    /**
     * This function is used to set menu title, request multiple permissions from user and
     * activates camera after all permission is requested.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setTitle(R.string.camera_title);
        requestPermission();
        initCamera();
        (findViewById(R.id.btnTakePhoto)).setOnClickListener(this);
        (findViewById(R.id.btnViewPhoto)).setOnClickListener(this);
        (findViewById(R.id.btnFlipCamera)).setOnClickListener(this);
    }

    /**
     * Requests permission from user in regard of read external storage, write external storage and
     * camera.
     * Code reference on the topic of request multiple permissions:
     * @see <a href="https://www.youtube.com/watch?v=nkayHRT8D_w&ab_channel=Foxandroid">
     * How to request multiple permissions</a>
     * <a href="https://developer.android.com/training/permissions/requesting">
     * Android Studio: Request app permission</a>
     */
    private void requestPermission() {
        //Check if every permission is granted from the beginning
        isReadPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            isWritePermissionGranted = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED;
        } else {
            isWritePermissionGranted = true;
        }
        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;

        //In the case permission is not granted, add the permission to ArrayList and launch permission request for each
        ArrayList<String> permissionRequest = new ArrayList<>();
        if (!isReadPermissionGranted) {
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!isWritePermissionGranted) {
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!isCameraPermissionGranted) {
            permissionRequest.add(Manifest.permission.CAMERA);
        }
        if (!permissionRequest.isEmpty()) {
            mPermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));
        }
    }

    /**
     * Requests a CameraProvider and check for CameraProvider availability
     * Code reference of CameraX api:
     * @see <a href="https://developer.android.com/training/camerax/preview">
     * Implement a preview: Request a camera provider</a>
     */
    private void initCamera() {
        // request a camera provider
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        // verify that the initialization succeeded when the view is created
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    /**
     * Select a camera and bind the lifecycle and use cases
     * @param cameraProvider ProcessCameraProvider
     * @see <a href="https://developer.android.com/training/camerax/preview">
     * Implement a preview: Request a camera provider</a>
     */
    private void bindPreview(ProcessCameraProvider cameraProvider) {
        // unbind all bound use cases
        cameraProvider.unbindAll();
        // create a Preview
        previewView = findViewById(R.id.viewCamera);
        Preview preview = new Preview.Builder().build();
        /* specify camera lens facing option (lens facing is back by default but user can change by
         clicking on flipping button) */
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build();
        // provide a surface for Preview
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();
        // Bind the selected camera and any use cases to lifecycle
        cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview);
    }

    /**
     * Event click listener for each button
     * @param view View
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTakePhoto) {
            capturePhoto();
        } else if (view.getId() == R.id.btnViewPhoto) {
            viewPhoto();
        } else {
            flipCameraLens();
        }
    }

    /**
     * Captures photo and saves the images to shared MediaStore.
     * Code reference:
     * @see <a href="https://developer.android.com/training/camerax/take-photo">Image capture: Implementation</a>
     * <a href="https://developer.android.com/reference/androidx/camera/core/ImageCapture.OutputFileOptions.Builder">
     *  ImageCapture.OutputFileOptions.Builder: Public constructors</a>
     */
    private void capturePhoto() {
        // Creating image display name in external storage starting with herb and current date
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String strDate = dateFormat.format(date);
        String displayName = "herb" + strDate + ".jpg";

        // Sets the values of each column and inserts the data to that column
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, displayName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri imageCollection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        // Takes picture and saves the images output to MediaStore
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions
                .Builder(
                getContentResolver(),
                imageCollection,
                contentValues).build();
        imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Toast.makeText(CameraActivity.this, "Image Saved Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(CameraActivity.this, "Error with saving images",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Launches ImageGalleryActivity
     */
    private void viewPhoto() {
        Intent intent = new Intent(CameraActivity.this, ImageGalleryActivity.class);
        intent.putExtra(LENS_FACING, lensFacing);
        startActivity(intent);
    }

    /**
     * Flips front or back camera lens and re-initialise camera to update with the lens-facing
     */
    private void flipCameraLens() {
        if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            lensFacing = CameraSelector.LENS_FACING_FRONT;
        } else {
            lensFacing = CameraSelector.LENS_FACING_BACK;
        }
        initCamera();
    }
}