import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindRoffelsComponent } from './find-roffels.component';

describe('FindRoffelsComponent', () => {
  let component: FindRoffelsComponent;
  let fixture: ComponentFixture<FindRoffelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindRoffelsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindRoffelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
