package blobQuickstart.blobAzureApp;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

/* *************************************************************************************************************************
* Summary: This application demonstrates how to use the Blob Storage service.
* It does so by creating a container, creating a file, then uploading that file, listing all files in a container, 
* and downloading the file. Then it deletes all the resources it created
* *************************************************************************************************************************
*/
public class AzureApp 
{
	/* *************************************************************************************************************************
	* Instructions: Update the storageConnectionString variable with your AccountName and Key and then run the sample.
	* *************************************************************************************************************************
	*/
	public static final String storageConnectionString =
	"DefaultEndpointsProtocol=https;" +
	"AccountName=ripplestoragewebtest;" +
	"AccountKey=HXSANTEU0F+W3DtfriRg6bcW4AYsxP7cbrAd1cxKvT3tlx7BZ5HUKiq5Wh+fNvTj6/aVtL8DGvKNXmCjgvEbqA==";


	public static void main( String[] args )
	{		
		AzureApp app = new AzureApp();
		File archivo;
		try {
			archivo = File.createTempFile("file-upload", ".txt");
			app.uploadFile(archivo);
			
			//test donwload file			
			app.downloadFile(archivo);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param archivo
	 */
	public void uploadFile(File archivo) {
		System.out.println(" ####################### Inicio uploadFile #######################");
		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container=null;
		
		File sourceFile = null;
		
		try {
			
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("quickstartcontainer");

			// Create the container if it does not exist with public access.
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());		    

			//Creating a sample file
			sourceFile = File.createTempFile("file-upload", ".txt");
			System.out.println("Creating a sample file at: " + sourceFile.toString());
			Writer output = new BufferedWriter(new FileWriter(sourceFile));
			output.write("Hello Azure!");
			output.close();

			//Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference(sourceFile.getName());

			//Creating blob and uploading file to it
			System.out.println("Uploading the sample file ");
			blob.uploadFromFile(sourceFile.getAbsolutePath());

			//Listing contents of container
			for (ListBlobItem blobItem : container.listBlobs()) {
				System.out.println("URI of blob is: " + blobItem.getUri());
			}
			
			//call to donwload file
			downloadFile(sourceFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" ####################### Fin uploadFile #######################");
	}
	
	/**
	 * 
	 * @param sourceFile
	 */
	public void downloadFile(File sourceFile) {
		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container=null;
		
		File downloadedFile = null;
				
		try {				
				storageAccount = CloudStorageAccount.parse(storageConnectionString);
				blobClient = storageAccount.createCloudBlobClient();
				container = blobClient.getContainerReference("quickstartcontainer");
				
				//Getting a blob reference
				CloudBlockBlob blob = container.getBlockBlobReference(sourceFile.getName());
				
				downloadedFile = new File(sourceFile.getParentFile(), "file-donwload.txt");
				blob.downloadToFile(downloadedFile.getAbsolutePath());
				
				System.out.println("successful download.. ");
				
			}catch (StorageException ex)
			{
				System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
			}
			catch (Exception ex) 
			{
				System.out.println(ex.getMessage());
			}			
		
	}
}
