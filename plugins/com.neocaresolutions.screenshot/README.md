To install in a Cordova 3.0+ project,

    cordova plugin add https://bitbucket.org/chitrapongsew/screenshotplugin

For Android, add the plugin for Files, [http://cordova.apache.org/docs/en/3.3.0/cordova_file_file.md.html#File](http://cordova.apache.org/docs/en/3.3.0/cordova_file_file.md.html#File)

    cordova plugin add org.apache.cordova.file
    cordova plugin add org.apache.cordova.file-transfer

Add the following xml snippet in 'app/res/xml/config.xml'
    
    <feature name="File">
        <param name="android-package" value="org.apache.cordova.file.FileUtils" />
    </feature>

     <feature name="FileTransfer">
        <param name="android-package" value="org.apache.cordova.filetransfer.FileTransfer" />
     </feature>


Add the xml snippet in 'app/AndroidManifest.xml'

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />