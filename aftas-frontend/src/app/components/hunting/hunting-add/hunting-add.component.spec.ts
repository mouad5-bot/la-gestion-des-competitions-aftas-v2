import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuntingAddComponent } from './hunting-add.component';

describe('HuntingAddComponent', () => {
  let component: HuntingAddComponent;
  let fixture: ComponentFixture<HuntingAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HuntingAddComponent]
    });
    fixture = TestBed.createComponent(HuntingAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
