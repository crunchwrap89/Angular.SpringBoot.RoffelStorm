import { Component, OnInit } from '@angular/core';
import { UploadFilesService } from 'src/app/services/upload-files.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { TokenStorageService } from '../../_services/token-storage.service';
import { Observable } from 'rxjs';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
 


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
  closeResult = '';
  url = '';

  fileInfos: Observable<any>;

  constructor(private uploadService: UploadFilesService, private token: TokenStorageService, private modalService: NgbModal) { }

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

  popImage(content, path) { 
    this.modalService.open(content, { windowClass : "myCustomModalClass"}).result.then((result) => { 
      this.closeResult = `Closed with: ${result}`; 
    }, (reason) => { 
      this.closeResult =  
         `Dismissed ${this.getDismissReason(reason)}`; 
    });
    this.url = "../assets/" + path;
  }

  private getDismissReason(reason: any): string { 
    if (reason === ModalDismissReasons.ESC) { 
      return 'by pressing ESC'; 
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) { 
      return 'by clicking on a backdrop'; 
    } else { 
      return `with: ${reason}`; 
    } 
  } 

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.fileInfos = this.uploadService.getFilesByUserId(this.currentUser.id);
    console.log(this.fileInfos[0].path);
  }
  
}


