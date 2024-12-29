/* Download an image as a say JPG from a blob.

For that, copy/paste the below code into a developer console and then the image gets downloaded

Works e.g. on https://www.artrenewal.org
*/


// Get the image element
const imageElement = document.getElementById('image');

// Fetch the blob data from the URL
fetch(imageElement.src)
  .then(response => response.blob())
  .then(blob => {
    // Create a link element
    const link = document.createElement('a');

    // Create an Object URL for the blob
    const url = URL.createObjectURL(blob);

    // Set the link's href to the Object URL
    link.href = url;

    // Set the download attribute to save as a file
    link.download = 'image.jpg'; // Change the file name as needed

    // Append the link to the body
    document.body.appendChild(link);

    // Programmatically click the link to trigger download
    link.click();

    // Cleanup: remove the link and revoke the Object URL
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  })
  .catch(error => console.error('Error downloading the image:', error));

