import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimationSwitchComponent } from './animation-switch.component';

describe('AnimationSwitchComponent', () => {
  let component: AnimationSwitchComponent;
  let fixture: ComponentFixture<AnimationSwitchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimationSwitchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimationSwitchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
