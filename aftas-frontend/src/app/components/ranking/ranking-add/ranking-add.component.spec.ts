import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RankingAddComponent } from './ranking-add.component';

describe('RankingAddComponent', () => {
  let component: RankingAddComponent;
  let fixture: ComponentFixture<RankingAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RankingAddComponent]
    });
    fixture = TestBed.createComponent(RankingAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
