import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import {Observable} from 'rxjs/Rx'
import 'rxjs/add/observable/of';
import { animate, state, style, transition, trigger } from '@angular/animations';
import {TaharokatService} from "../taharokat.service";
import {TaharokatModel} from "../taharokat.model";
// @ts-ignore
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'taharokat-list',
  templateUrl: './taharokat-list.html',
  styleUrls: ['./taharokat-list.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('expanded', style({ visibility: 'visible' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class TaharokatList implements OnInit {
  displayedColumns =
    ['sahmName','jumpNesbat','jumpSaraneh','plusD','plusN','minusN','m','mBd'
    ];
  itemChange: EventEmitter<string> = new EventEmitter<string>();
  defaultPageSize = 10;
  @ViewChild(MatPaginator, {static: true})
  paginator: MatPaginator;
  dataSource: TaharokatListDataSource | null;
  expandedElement: any;
  sortColumn = '';
  sortMode = '';

  constructor(private taharokatService:TaharokatService) {
  }

  doSort(sortColumn: string) {
    if (sortColumn !== this.sortColumn && this.sortColumn !== '') {
      this.sortMode = '';
      this.sortColumn = '';
      this.dataSource.sortColumn = this.sortColumn;
      this.dataSource.sortMode = this.sortMode;
      this.itemChange.emit('Data Changed');
      return;
    }
    if (this.sortMode === 'ASC') {
      this.sortMode = '';
      this.sortColumn = '';
    } else if (this.sortMode === '') {
      this.sortMode = 'DESC';
      this.sortColumn = sortColumn;
    } else if (this.sortMode === 'DESC') {
      this.sortMode = 'ASC';
      this.sortColumn = sortColumn;
    }
    this.dataSource.sortColumn = this.sortColumn;
    this.dataSource.sortMode = this.sortMode;
    this.itemChange.emit('Data Changed');
  }

  isSortVisible(sortColumn: string, sortMode: string) {
    return sortColumn === this.sortColumn && sortMode === this.sortMode;
  }

  onRowClick(row) {
    if (this.expandedElement === row) {
      this.expandedElement = null;
    } else {
      this.expandedElement = row;
    }
  }
  ngOnInit() {
    this.dataSource = new TaharokatListDataSource(this.taharokatService, this.paginator, this.itemChange);
  }


  toDateString(datestr: string) {
    const date = new Date(Number(datestr)),
      year = date.getFullYear(),
      month = (date.getMonth() + 1).toString(),
      formatedMonth = (month.length === 1) ? ('0' + month) : month,
      day = date.getDate().toString(),
      formatedDay = (day.length === 1) ? ('0' + day) : day,
      hour = date.getHours().toString(),
      formatedHour = (hour.length === 1) ? ('0' + hour) : hour,
      minute = date.getMinutes().toString(),
      formatedMinute = (minute.length === 1) ? ('0' + minute) : minute,
      second = date.getSeconds().toString(),
      formatedSecond = (second.length === 1) ? ('0' + second) : second;
    return formatedDay + '-' + formatedMonth + '-' + year + ' ' + formatedHour + ':' + formatedMinute + ':' + formatedSecond;
  };
}

export class TaharokatListDataSource extends DataSource<any> {
  public totalRow = 0;
  public data = [];
  sortColumn = '';
  sortMode = '';
  searchText = '';

  fromDate: number;
  toDate: number;

  constructor(private taharokatService: TaharokatService,
              public paginator: MatPaginator, private itemChange: EventEmitter<string>
  ) {
    super();
    let date: Date = new Date();
    date.setDate(date.getDate() - 7);
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);
    this.fromDate = date.getTime();
    date = new Date();
    date.setHours(23);
    date.setMinutes(59);
    date.setSeconds(59);
    date.setMilliseconds(999);
    this.toDate = date.getTime();
  }

  connect(): Observable<TaharokatModel[]> {
    const displayDataChanges = [
      this.paginator.page,

      this.itemChange
    ];
    return Observable.merge(...displayDataChanges)
      .startWith(null)
      .switchMap(() => {
        return this.taharokatService.showTaharokatModelOverviewPage(
          this.searchText,
          this.paginator.pageSize,
          this.paginator.pageIndex
        );

      })
      .map(data => {
        this.totalRow = data.totalElements;
        this.data = data.content;

        return data.content;
      })
      .catch(() => {
        return Observable.of(null);
      });
  }

  disconnect() {
  }
}
