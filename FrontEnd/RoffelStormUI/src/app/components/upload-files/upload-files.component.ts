import { Component, OnInit } from '@angular/core';
import { UploadFilesService } from 'src/app/services/upload-files.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { TokenStorageService } from '../../_services/token-storage.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-upload-files',
  templateUrl: './upload-files.component.html',
  styleUrls: ['./upload-files.component.css']
})
export class UploadFilesComponent implements OnInit {
  currentUser: any;
  selectedFiles: FileList;
  progressInfos = [];
  message = '';

  fileInfos: Observable<any>;

  constructor(private uploadService: UploadFilesService, private token: TokenStorageService) { }

  selectFiles(event): void {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }
  uploadFiles(): void {
    this.message = '';
  
    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i]);
    }
  }
  upload(idx, file): void {
    this.progressInfos[idx] = { value: 0, fileName: file.name };
  
    this.uploadService.upload(file, this.currentUser.id).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.fileInfos = this.uploadService.getFiles();
        }
      },
      err => {
        this.progressInfos[idx].value = 0;
        this.message = 'Could not upload the file:' + file.name;
      });
      console.log(this.currentUser.id);
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.fileInfos = this.uploadService.getFilesByUserId(this.currentUser.id);
    console.log(this.fileInfos[0].path);
  }
  
}


