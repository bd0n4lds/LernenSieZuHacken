$hostname = Read-Host -Prompt 'Enter the lane hostname or IP address'

Write-Host ""

$Processes = Get-WmiObject -Class Win32_Process -ComputerName $hostname -Filter "name like 'multi%'"
foreach ($process in $processes) {
    if ($process.ProcessName -ne 'MultiRes.exe'){
        $returnval = $process.terminate()
 
        if($returnval.returnvalue -eq 0) {
            write-host "The process" $process.ProcessName "terminated successfully"
        }
        else {
            write-host "The process" $process.ProcessName "failed to terminate"
        }
    }
}

Copy-Item –Path \\longosfs\Filer\Installs_Drivers\Installs\SCCM\POS_MCM_Update\Test_ACCEO_Tender_Retail_Update_Kit_4_2_17_5225\* –Destination \\$hostname\c$\MerchantConnectMulti\ -Recurse -Force -PassThru | Tee-Object -FilePath .\$hostname.txt
Copy-Item -Path .\$hostname.txt -Destination \\$hostname\c$\posupgrade\copymcm5225.log

Restart-Computer -ComputerName $hostname -Force