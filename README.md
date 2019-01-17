# How to run this project

## Prerequisites

To complete this tutorial:

* Install [Eclipse](http://www.eclipse.org/downloads/)


If you don't have an Azure subscription, create a [free account](https://azure.microsoft.com/free/?WT.mc_id=A261C142F) before you begin.

## Create a storage account using the Azure portal

First, create a new general-purpose storage account to use for this tutorial. 

*  Go to the [Azure portal](https://portal.azure.com) and log in using your Azure account. 
*  On the Hub menu, select **New** > **Storage** > **Storage account - blob, file, table, queue**. 
*  Enter a name for your storage account. The name must be between 3 and 24 characters in length and may contain numbers and lowercase letters only. It must also be unique.
*  Set `Deployment model` to **Resource manager**.
*  Set `Account kind` to **General purpose**.
*  Set `Performance` to **Standard**. 
*  Set `Replication` to **Locally Redundant storage (LRS)**.
*  Set `Storage service encryption` to **Disabled**.
*  Set `Secure transfer required` to **Disabled**.
*  Select your subscirption. 
*  For `resource group`, create a new one and give it a unique name. 
*  Select the `Location` to use for your storage account.
*  Check **Pin to dashboard** and click **Create** to create your storage account. 

After your storage account is created, it is pinned to the dashboard. Click on it to open it. Under SETTINGS, click **Access keys**. Select a key and copy the CONNECTION STRING to the clipboard, then paste it into Notepad for later use.

## Modify the connection string in the AzureApp.java file 

Open this solution, and in the AzureApp.java file, change the value for connection string to the one retrieved from the portal. 

At this point, you can run this application. It creates its own file to upload and download.